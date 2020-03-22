/**
 * 
 */
package com.nokia.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

import org.junit.Test;

/**
 * @author yanachen
 * @create 2020-03-20 18:47:50
 * 
 * java NIO�ܵ���2���߳�֮��ĵ�����������
 * Pipe��һ��sourceͨ����һ��sinkͨ�������ݻᱻд��sinkͨ������sourceͨ����ȡ
 * 
 * Thread A --->Pipe (SinkChannel- SourceChannel) --->ThreadB
 */
public class TestPipe {
	@Test
	public void test() throws IOException {
		//1.��ȡ�ܵ�
		Pipe pipe=Pipe.open();
		
		//2.���������е�����д��ܵ�
		ByteBuffer buf=ByteBuffer.allocate(1024);
		
		Pipe.SinkChannel sinkChannel=pipe.sink();
		buf.put("ͨ������ܵ���������".getBytes());
		buf.flip();
		sinkChannel.write(buf);		
		
		//3.��ȡ�������е�����
		Pipe.SourceChannel sourceChannel=pipe.source();
		buf.flip();
		int len=sourceChannel.read(buf);
		System.out.println(new String(buf.array(),0,len));
		
		sourceChannel.close();
		sinkChannel.close();
		
		
	}

}
