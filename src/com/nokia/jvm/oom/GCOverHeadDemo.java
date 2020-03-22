/**
 * 
 */
package com.nokia.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanachen
 * @create 2020-01-31 18:17:21
 * JVM������ʾ����
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 
 * GC����ʱ�����ʱ���׳�Out of Memory error,�����Ķ����ǳ���98%��ʱ��������GC���һ����˲���2%�Ķ��ڴ�  GC��ͷ
 * �������GC��ֻ�����˲���2%�ļ�������²Ż��׳������粻�׳�GC Overhead limit exceeded �ᷢ��ʲô��
 * �Ǿ�������Ķ��ڴ�ܿ챻�ٴ���������ʹGC�ٴ�ִ�У��������γɶ���ѭ����CPUʹ����һֱ��100%����GCȴû���κ�Ч��
 */
public class GCOverHeadDemo {

	/**
	 * @param args
	 * java.lang.OutOfMemoryError: GC overhead limit exceeded
	 */
	public static void main(String[] args) {
		int i=0;
		List<String> list=new ArrayList<String>();
		try {
			while(true) {			
//				list.add(String.valueOf(i).intern()); //�����i,��Java heap space, at java.util.ArrayList.add(ArrayList.java:462)
				list.add(String.valueOf(++i).intern());//�����++i,String.valueOf �ײ��� java.lang.Integer.toString(Integer.java:401),��java.lang.OutOfMemoryError: GC overhead limit exceeded
			}
		} catch (Throwable e) {
			System.out.println("********i:"+i);
			throw e;
		}

	}

}
