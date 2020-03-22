/**
 * 
 */
package com.nokia.jvm.oom;

import java.nio.ByteBuffer;

/**
 * @author yanachen
 * @create 2020-01-31 19:37:18
 * ��Ƶ����
 * ���ò����� -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 
 * ��������java.lang.OutOfMemoryError: Direct buffer memory
 * 
 * ����ԭ��
 * дNIO���򾭳�ʹ��ByteBuffer����ȡ����д�����ݣ�����һ�ֻ���ͨ����Channel)�뻺����(Buffer)��I/O��ʽ
 * ������ʹ��Native������ֱ�ӷ�������ڴ棬Ȼ��ͨ��һ���洢��Java�������DirectByteBuffer������Ϊ����ڴ�����ý��в�����
 * ��������һЩ����������������ܣ���Ϊ��������Java�Ѻ�Native�������ظ������ݡ�
 * ByteBuffer.allocate(capacity) ��һ�ַ�ʽ�Ƿ���JVM���ڴ棬����GC��Ͻ��Χ��������Ҫ���������ٶ���Խ���
 * ByteBuffer.allocateDirect(capacity) �ڶ��ַ�ʽ�Ƿ���OS�����ڴ棬������GC��Ͻ��Χ�����ڲ���Ҫ���������ٶ���ԽϿ�
 * ��������Ϸ��䱾���ڴ棬���ڴ����ʹ�ã���ôJVM����Ҫִ��GC��DirectByteBuffer�����ǾͲ��ᱻ����
 * ��ʱ����ڴ���㣬�������ڴ�����Ѿ�ʹ�ù��ˣ��ٴγ��Է��䱾���ڴ�ͻ����OutOfMemory,����ֱ�ӱ����ˡ�
 */
public class DirectBufferMemory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("���õ�MaxDirectMemoryΪ"+sun.misc.VM.maxDirectMemory()/(double)1024/1024+"MB");//Ĭ�������ڴ��1/4
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//-XX:MaxDirectMemorySize=5m ��������Ϊ5MB����ʵ��ʹ��6MB������ʹ��
		ByteBuffer bb=ByteBuffer.allocateDirect(60*1024*1024);
		bb.clear();

	}

}
