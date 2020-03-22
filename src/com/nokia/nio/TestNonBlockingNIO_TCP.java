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
 * NIO�ķ�����ʽ����ͨ�ţ� ����������ˣ��������ͻ���
 * selector�������channel��״̬
 * ��ע����selector�ϵ�channel׼��������ŷ��͸��������˵�һ�����߶���߳���,�ڴ�֮ǰ�������ø�ʲô�͸�ʲô�����ÿ��Եȴ������û���ַ�ռ���ں˵�ַ�ռ䣩
 * 
 * һ��ʹ��NIO�������ͨ�ŵ��������ģ�
 * 1.ͨ����Channel������������
 *   java.nio.channels.Channel�ӿ�
 *        |--SelectableChannel
 *        	 |--SocketChannel   (TCP)
 *           |--ServerSocketChannel
 *           |--DatagramChannel   (UDP)
 *           
 *           |--Pipe.SinkChannel
 *           |--Pipe.SourceChannel
 * 2.������(Buffer)���������ݵĴ�ȡ
 * 3.ѡ����(Selector)����SelectableChannel�Ķ�·�����������ڼ��SelectableChannel��IO״��
 */
public class TestNonBlockingNIO_TCP {
	
	
	//�ͻ���
	@Test
	public void client() throws IOException {
		//1.��ȡͨ��(ip and port����׽���)
		SocketChannel sChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));		
		
		//2.�л�������ģʽ
		sChannel.configureBlocking(false);
		
		//3.����ָ����С�Ļ�����
		ByteBuffer buf=ByteBuffer.allocate(1024);
		
		//4.�������ݵ�������  ---������������
		Scanner scan=new Scanner(System.in);
		while(scan.hasNext()) {
			String str=scan.next();			
			buf.put((new Date().toString()+"\n"+str).getBytes());
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		//5.�ر�ͨ��		
		sChannel.close();
		
	}
	
	//�����
	@Test
	public void server() throws IOException {
		//1.��ȡͨ��
		ServerSocketChannel ssChannel=ServerSocketChannel.open();		
		
		//2.�л�������ģʽ
		ssChannel.configureBlocking(false);
		
		//3.������
		ssChannel.bind(new InetSocketAddress(9898));
		
		//4.��ȡѡ����
		Selector selector=Selector.open();
		
		//5.��ͨ��ע�ᵽѡ�����ϣ�����ָ��"���������¼�"
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		//6.��ѯʽ�Ļ�ȡѡ�������Ѿ���׼�����������¼�
		while(selector.select()>0) {
			
			//7.��ȡ��ǰѡ����������ע���ѡ������Ѿ����ļ����¼���
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()) {
				
				//8��ȡ׼���������¼�
				SelectionKey sk=it.next();
				
				//9.�жϾ�����ʲô�¼�׼������
				if(sk.isAcceptable()) {
					//10.�������վ���������ȡ�ͻ�������
					SocketChannel sChannel=ssChannel.accept();
					
					//11.�л���������ģʽ
					sChannel.configureBlocking(false);
					
					//12.����ͨ��ע�ᵽѡ������
					sChannel.register(selector, SelectionKey.OP_READ);
					
				}else if(sk.isReadable()) {
					//13.��ȡ��ǰѡ�����ϵĶ�����״̬��ͨ��
					SocketChannel sChannel=(SocketChannel) sk.channel();
					
					//14.��ȡ����
					ByteBuffer buf=ByteBuffer.allocate(1024);
					
					int len=0;
					
					while((len=sChannel.read(buf))>0) {
						buf.flip();
						System.out.println(new String(buf.array(),0,len));
						buf.clear();
					}
				}
			}
			
			//15.ȡ��ѡ���
			it.remove();
		}		
		
	}
	

}
