/**
 * 
 */
package com.nokia.jvm.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author yanachen
 * @CreateTime 2020-01-31 12:40:04
 * java�ṩ��4���������ͣ����������յ�ʱ�򣬶����Լ����Ե��ص�
 * ReferenceQueue������������ù����ģ�û��ReferenceQueueһ���������С�
 * 
 * �������õ�ʱ�����ָ�������Ķ��У���GC�ͷŶ����ڴ��ʱ�򣬻Ὣ���ü��뵽���ö��С�
 * ���������ĳ���������Ѿ������뵽���ö��У���ô�����������õĶ�����ڴ����֮ǰ��ȡ��Ҫ���ж���
 * ���൱��һ��֪ͨ���ơ�������AOP�ĺ���֪ͨ
 * 
 * �����������ö����������ݵ�ʱ����ζ������ָ��Ķ��ڴ��еĶ��󱻻��ա�ͨ�����ַ�ʽ��JVM���������ڶ������ٺ���һЩ�����Լ����������顣
 * 
 * ���һ����������������ã���ô������ͬû���κ�����һ������ʱ�����Ա��������ա�
 * �����ñ�������ö�������ʹ��
 * �����õ���Ҫ�����Ǹ��ٶ����������յ�״̬��PhantomReference��get�������Ƿ���null
 */
public class PhantomReferenceDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object o1=new Object();
		ReferenceQueue<Object> queue=new ReferenceQueue<Object>();		
		PhantomReference<Object> o2=new PhantomReference<Object>(o1,queue);
		System.out.println(o1);
		System.out.println(o2.get());//����null	
		System.out.println(queue.poll());
		System.out.println();
		
		o1=null;
		System.gc();
		System.out.println(o1);
		System.out.println(o2.get());//����null
		System.out.println(queue.poll());//GC���պ󣬽������յ������÷ŵ�ReferenceQueue�java.lang.ref.PhantomReference@6d06d69c
	}

}
