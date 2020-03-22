package com.nokia.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
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
 *           |--DatagramChannel   (UDP)  ���ݱ�
 *           
 *           |--Pipe.SinkChannel
 *           |--Pipe.SourceChannel
 * 2.������(Buffer)���������ݵĴ�ȡ
 * 3.ѡ����(Selector)����SelectableChannel�Ķ�·�����������ڼ��SelectableChannel��IO״��
 */
public class TestNonBlockingNIO_UDP {	
	
	//���Ͷ�
	@Test
	public void send() throws IOException {
		DatagramChannel dc=DatagramChannel.open();
		//2.�л�������ģʽ
		dc.configureBlocking(false);
		
		//3.����ָ����С�Ļ�����
		ByteBuffer buf=ByteBuffer.allocate(1024);
		
		//4.�������ݵ�������  ---������������
		Scanner scan=new Scanner(System.in);
		while(scan.hasNext()) {
			String str=scan.nextLine();		
			buf.put((new Date().toString()+"\n"+str).getBytes());
			buf.flip();
			dc.send(buf, new InetSocketAddress("127.0.0.1",9898));
			buf.clear();
		}
		
		//5.�ر�ͨ��		
		dc.close();
		
	}
	
	//���ն�
	@Test
	public void receive() throws IOException {
		//1.��ȡͨ��
		DatagramChannel dc=DatagramChannel.open();		
		
		//2.�л�������ģʽ
		dc.configureBlocking(false);
		
		//3.������
		dc.bind(new InetSocketAddress(9898));
		
		//4.��ȡѡ����
		Selector selector=Selector.open();
		
		//5.��ͨ��ע�ᵽѡ�����ϣ�����ָ��"���������¼�"
		dc.register(selector, SelectionKey.OP_READ);
		
		//6.��ѯʽ�Ļ�ȡѡ�������Ѿ���׼�����������¼�
		while(selector.select()>0) {
			
			//7.��ȡ��ǰѡ����������ע���ѡ������Ѿ����ļ����¼���
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()) {
				
				//8��ȡ׼���������¼�
				SelectionKey sk=it.next();
				
				//9.�жϾ�����ʲô�¼�׼������
				if(sk.isReadable()) {
										
					//10.��������
					ByteBuffer buf=ByteBuffer.allocate(1024);
					dc.receive(buf);
					buf.flip();
					System.out.println(new String(buf.array(),0,buf.limit()));
					buf.clear();
				}
			}
			
			//15.ȡ��ѡ���
			it.remove();
		}		
		
	}
	

}
