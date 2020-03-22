package com.nokia.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/*
 * NIO的非阻塞式网络通信：
 * selector用来检测channel的状态
 * 当注册在selector上的channel准备就绪后才发送给服务器端的一个或者多个线程上,在此之前服务器该干什么就干什么，不用可以等待。（用户地址空间和内核地址空间）
 * 
 * 一、使用NIO完成网络通信的三个核心：
 * 1.通道（Channel）：负责连接
 *   java.nio.channels.Channel接口
 *        |--SelectableChannel
 *        	 |--SocketChannel   (TCP)
 *           |--ServerSocketChannel
 *           |--DatagramChannel   (UDP)
 *           
 *           |--Pipe.SinkChannel
 *           |--Pipe.SourceChannel
 * 2.缓冲区(Buffer)：负责数据的存取
 * 3.选择器(Selector)：是SelectableChannel的多路复用器，用于监控SelectableChannel的IO状况
 */
public class TestBlockingNIO {
	//客户端
	@Test
	public void client() throws IOException {
		//1.获取通道(ip and port组成套接字)
		SocketChannel sChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));
		
		FileChannel inChannel=FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		
		//2.分配指定大小的缓冲区
		ByteBuffer buf=ByteBuffer.allocate(1024);
		
		//3.读取本地文件，并发送到服务器
		while(inChannel.read(buf)!=-1) {
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		//4.关闭通道
		inChannel.close();
		sChannel.close();
		
	}
	
	//服务端
	@Test
	public void server() throws IOException {
		//1.获取通道
		ServerSocketChannel ssChannel=ServerSocketChannel.open();
		
		FileChannel outChannel=FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.CREATE,StandardOpenOption.WRITE);
		
		//2.绑定连接
		ssChannel.bind(new InetSocketAddress(9898));
		
		//3.获取客户端连接的通道
		SocketChannel  sChannel=ssChannel.accept();
		
		//4.分配指定大小的缓冲区
		ByteBuffer buf=ByteBuffer.allocate(1024);
		
		//5.接受客户端的数据，并保存到本地
		while(sChannel.read(buf)!=-1) {
			buf.flip();
			outChannel.write(buf);
			buf.clear();
		}
		
		//6.关闭通道
		sChannel.close();
		outChannel.close();
		ssChannel.close();
	}
	

}
