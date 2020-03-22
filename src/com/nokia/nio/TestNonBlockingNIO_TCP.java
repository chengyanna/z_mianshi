package com.nokia.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

/*
 * NIO的非阻塞式网络通信： 先启动服务端，再启动客户端
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
public class TestNonBlockingNIO_TCP {
	
	
	//客户端
	@Test
	public void client() throws IOException {
		//1.获取通道(ip and port组成套接字)
		SocketChannel sChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));		
		
		//2.切换非阻塞模式
		sChannel.configureBlocking(false);
		
		//3.分配指定大小的缓冲区
		ByteBuffer buf=ByteBuffer.allocate(1024);
		
		//4.发送数据到服务器  ---类似于聊天室
		Scanner scan=new Scanner(System.in);
		while(scan.hasNext()) {
			String str=scan.next();			
			buf.put((new Date().toString()+"\n"+str).getBytes());
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		//5.关闭通道		
		sChannel.close();
		
	}
	
	//服务端
	@Test
	public void server() throws IOException {
		//1.获取通道
		ServerSocketChannel ssChannel=ServerSocketChannel.open();		
		
		//2.切换非阻塞模式
		ssChannel.configureBlocking(false);
		
		//3.绑定连接
		ssChannel.bind(new InetSocketAddress(9898));
		
		//4.获取选择器
		Selector selector=Selector.open();
		
		//5.将通道注册到选择器上，并且指定"监听接收事件"
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		//6.轮询式的获取选择器上已经“准备就绪”的事件
		while(selector.select()>0) {
			
			//7.获取当前选择器中所有注册的选择键（已就绪的监听事件）
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()) {
				
				//8获取准备就绪的事件
				SelectionKey sk=it.next();
				
				//9.判断具体是什么事件准备就绪
				if(sk.isAcceptable()) {
					//10.若“接收就绪”，获取客户端连接
					SocketChannel sChannel=ssChannel.accept();
					
					//11.切换到非阻塞模式
					sChannel.configureBlocking(false);
					
					//12.将该通道注册到选择器上
					sChannel.register(selector, SelectionKey.OP_READ);
					
				}else if(sk.isReadable()) {
					//13.获取当前选择器上的读就绪状态的通道
					SocketChannel sChannel=(SocketChannel) sk.channel();
					
					//14.读取数据
					ByteBuffer buf=ByteBuffer.allocate(1024);
					
					int len=0;
					
					while((len=sChannel.read(buf))>0) {
						buf.flip();
						System.out.println(new String(buf.array(),0,len));
						buf.clear();
					}
				}
			}
			
			//15.取消选择键
			it.remove();
		}		
		
	}
	

}
