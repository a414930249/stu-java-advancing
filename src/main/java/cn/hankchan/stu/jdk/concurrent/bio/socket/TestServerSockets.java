package cn.hankchan.stu.jdk.concurrent.bio.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * Java BIO 实现：ServerSocket和Socket
 * @author Hank_  <p>Email:hankchan101@gmail.com</p>
 * @version 创建时间: 7 Nov 2016-21:35:05
 * <p>类说明:
 */
public class TestServerSockets {

	@Test
	public void testSocketClient() {
		Socket client = null;
		OutputStream out = null;
		DataOutputStream dos = null;
		try {
			client = new Socket("localhost", 9092);
			out = client.getOutputStream();
			dos = new DataOutputStream(out);
			dos.writeUTF("Hello Server, I am the client... ");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testServerSocket() {
		Socket client = null;
		InputStream in = null;
		DataInputStream dis = null;
		try {
			ServerSocket server = new ServerSocket(9092);
			while(true) {
				System.out.println("server is Listening... port at: " + server.getLocalPort());
				client = server.accept();
				System.out.println("the client port:" + client.getPort() + " is connected...");
				in = client.getInputStream();
				dis = new DataInputStream(in);
				System.out.println(dis.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
