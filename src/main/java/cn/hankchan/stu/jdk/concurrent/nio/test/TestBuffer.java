package cn.hankchan.stu.jdk.concurrent.nio.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * Buffer的基本用法
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 7 Nov 2016-01:20:31
 * <p>类说明: 使用Buffer读写数据一般遵循下面四个步骤：
 * 1. 写入数据到Buffer
 * 2. 调用flip()方法
 * 3. 从Buffer中读取数据
 * 4. 调用clear()方法或者compact()方法
 */
public class TestBuffer {

	/*
	 * Gathering Writes是指数据从多个buffer写入到同一个channel
	 * buffers数组是write()方法的入参，write()方法会按照buffer在数组中的顺序，将数据写入到channel，
	 * 注意只有position和limit之间的数据才会被写入。因此，如果一个buffer的容量为128byte，但是仅仅包含58byte的数据，
	 * 那么这58byte的数据将被写入到channel中。因此与Scattering Reads相反，Gathering Writes能较好的处理动态消息
	 */
	@Test
	public void testGather() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("d:/data/nio-data.txt", "rw");
		try {
			ByteBuffer header = ByteBuffer.allocate(128);
			ByteBuffer body = ByteBuffer.allocate(1024);
			ByteBuffer[] bufferArray = {header, body};
			FileChannel channel = aFile.getChannel();
			channel.write(bufferArray);
		} finally {
			aFile.close();
		}
	}
	
	/*
	 * 分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。
	 * 因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中。
	 */
	@Test
	public void testScatter() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("d:/data/nio-data.txt", "rw");
		try {
			ByteBuffer header = ByteBuffer.allocate(128);
			ByteBuffer body = ByteBuffer.allocate(1024);
			ByteBuffer[] bufferArray = {header, body};
			FileChannel channel = aFile.getChannel();
			channel.read(bufferArray);
		} finally {
			aFile.close();
		}
	}
	
	/*
	 * 从Buffer中读取数据，主要有两种方式：1.从Buffer读取数据到Channel；2.使用get()方法从Buffer读取数据
	 */
	@Test
	public void testReadFromBuffer() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("d:/data/nio-data.txt", "rw");
		try {
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(48);
			int bytesWritten = inChannel.write(buf);
			System.out.println(bytesWritten);
			byte aByte = buf.get();
			System.out.println(aByte);
		} finally {
			aFile.close();
		}
	}
	
	/*
	 * 向Buffer中写数据，主要有两种方式：1.从Channel写到Buffer；2.通过Buffer的put()方法写到Buffer里。
	 */
	@Test
	public void testWriteToBuffer() throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("d:/data/nio-data.txt", "rw");
		try {
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(48);
			// 1.从Channel写到Buffer的例子
			int bytesRead = inChannel.read(buf);
			System.out.println(bytesRead);
			// 2.通过put()方法写Buffer的例子
			buf.put((byte) 127);
		} finally {
			aFile.close();
		}
	}
	
	/*
	 * Buffer的三个属性：capacity，position，limit。
	 * capacity：读和写模式下，的意义是一样的。
	 * position：当写数据到Buffer时，position表示当前的位置，初始的position值为0，当一个byte、long等数据写到Buffer后，
	 *           position会向前移动到下一个可插入数据的Buffer单元。position最大可为capacity – 1。
	 * 			 当读取数据时，也是从某个特定位置读。当将Buffer从写模式切换到读模式，position会被重置为0.
	 * 			 当从Buffer的position处读取数据时，position向前移动到下一个可读的位置
	 * limit：在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据，此时limit等于capacity。
	 * 		   当切换到读模式时，limit表示你能最多读到多少数据。因此，当切换Buffer到读模式时，limit会被设置成写模式下的position值。
	 * 		   换句话说，你能读到之前写入的所有数据（limit被设置成已写数据的数量，这个值在写模式下就是position）
	 * 数据模型如下：
	 * 					Buffer Write Model                         Buffer Read Model
	 * 					__________________                         _________________
	 * 				   |__________________|           position--> |_________________|
	 * 				   |__________________|                       |_________________|
	 * 				   |__________________|                       |_________________|
	 * 	  position-->  |__________________|              limit -> |_________________|
	 * 				   |__________________|                       |_________________|
	 * 				   |__________________|                       |_________________|
	 * 				   |__________________|                       |_________________|
	 * 				   |__________________|                       |_________________|
	 * limit/capacity->|__________________|            capacity-> |_________________|
	 */
	public void testPositionLimitCapacity() {
		
	}
	
	/* 基本用法
	 * 当向Buffer写入数据时，Buffer会记录写下了多少数据，一旦需要读取，调用flip()方法从写模式切换到读模式
	 * 在读模式下，可以读取之前写入到Buffer的数据
	 * 一旦读完所有数据，需要清空缓冲区，以便再次写入。清空缓冲区两种方法：
	 * 调用clear()，清空整个缓冲区；调用compact()，只清除已经读过的数据，新写入的数据会放在未读数据的后面
	 */
	@Test
	public void baseUsingExample() throws IOException {
		RandomAccessFile aFile = null;
		try {
			aFile = new RandomAccessFile("d:/data/nio-data.txt", "rw");
			FileChannel inChannel = aFile.getChannel();
			// create Buffer with capacity of 48 bytes.
			// 分配指定字节capacity的示例
			ByteBuffer buf = ByteBuffer.allocate(48);
			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {
				System.out.println("Read " + bytesRead);
				// make buffer ready for read.
				buf.flip();
				while(buf.hasRemaining()) {
					// read 1 byte at once time
					System.out.println((char) buf.get());
				}
				buf.clear();
				bytesRead = inChannel.read(buf);
			}
		} finally {
			aFile.close();
		}
	}
}
