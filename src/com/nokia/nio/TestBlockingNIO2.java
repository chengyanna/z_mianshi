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
 * NIO�ķ�����ʽ����ͨ�ţ����������ܳɹ����뷴��
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
public class TestBlockingNIO2 {
	//�ͻ���
	@Test
	public void client() throws IOException {
		//1.��ȡͨ��
		SocketChannel sChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",9899));
		
		FileChannel inChannel=FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		
		//2.����ָ����С�Ļ�����
		ByteBuffer buf=ByteBuffer.allocate(1024);
		
		//3.��ȡ�����ļ��������͵�������
		while(inChannel.read(buf)!=-1) {
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		sChannel.shutdownInput();//��֪��������������ˣ�Ҫ��Ȼ��֪���������Ƿ����꣬һֱ��������״̬
		
		//���շ���˵ķ���
		int len=0;
		while((len=sChannel.read(buf))!=-1) {
			buf.flip();
			System.out.println(new String(buf.array(),0,len));
		}
		//4.�ر�ͨ��
		inChannel.close();
		sChannel.close();
		
	}
	
	//�����
	@Test
	public void server() throws IOException {
		//1.��ȡͨ��
		ServerSocketChannel ssChannel=ServerSocketChannel.open();
		
		FileChannel outChannel=FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.CREATE,StandardOpenOption.WRITE);
		
		//2.������
		ssChannel.bind(new InetSocketAddress(9899));
		
		//3.��ȡ�ͻ������ӵ�ͨ��
		SocketChannel  sChannel=ssChannel.accept();
		
		//4.����ָ����С�Ļ�����
		ByteBuffer buf=ByteBuffer.allocate(1024);
		
		//5.���ܿͻ��˵����ݣ������浽����
		while(sChannel.read(buf)!=-1) {
			buf.flip();
			outChannel.write(buf);
			buf.clear();
		}
		
		//���ͷ������ͻ���
		buf.put("�������˽������ݳɹ�".getBytes());
		buf.flip();
		sChannel.write(buf);
		
		//6.�ر�ͨ��
		sChannel.close();
		outChannel.close();
		ssChannel.close();
	}
	

}
