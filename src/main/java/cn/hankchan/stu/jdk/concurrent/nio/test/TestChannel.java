package cn.hankchan.stu.jdk.concurrent.nio.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 基本的Channel示例
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 7 Nov 2016-01:19:32
 * <p>类说明:
 */
public class TestChannel {

	public static void main(String[] args) {
		TestChannel channel = new TestChannel();
		channel.baseUsingExample();
	}
	
	public void baseUsingExample() {
		RandomAccessFile aFile;
		try {
		aFile = new RandomAccessFile("d:/data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			System.out.println("Read " + bytesRead);
			// flip()的调用，首先读取数据到Buffer，然后反转Buffer，接着再从Buffer中读取数据
			buf.flip();
			while(buf.hasRemaining()) {
				System.out.println((char) buf.get());
			}
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
