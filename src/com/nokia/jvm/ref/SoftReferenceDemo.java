/**
 * 
 */
package com.nokia.jvm.ref;

import java.lang.ref.SoftReference;

/**
 * @author yanachen
 * ����ֻ�������õĶ�����˵���ڴ湻�õ�ʱ��ͱ����������þͻ���
 * 
 * �����ú���������Ӧ������ Mybatis����
 * ����һ��Ӧ����Ҫ��Ҫ��ȡ�����ı���ͼƬ��
 * 	�����ÿ�ζ�ȡͼƬ����Ӳ�̶�ȡ������Ӱ������
 *  �����һ����ȫ�����ص��ڴ����п�������ڴ����
 *  
 *  ��ʱ�����û��������ÿ��Խ���������
 *  
 *  ���˼·����һ��HashMap������ͼƬ��·������ӦͼƬ���������������֮���ӳ���ϵ�����ڴ治��ʱJVM���Զ�������Щ����ͼƬ����ռ�õĿռ䣬�Ӷ���Ч����OOM����
 *  Map<String,SoftReference<Bitmap>> map=new HashMap<String,SoftReference<Bitmap>>();
 *
 */
public class SoftReferenceDemo {
	
//	 �ڴ湻�õ�ʱ��ͱ����������þͻ���
	public static void softRef_Memory_Enough() {
		Object o1=new Object();
		SoftReference<Object> o2=new SoftReference<Object>(o1);
		System.out.println(o1);
		System.out.println(o2.get());
		System.out.println();
		
		o1=null;
		System.gc();
		System.out.println(o1);
		System.out.println(o2.get());
		System.out.println("===============================");
	}

	/*
	 * JVM���ã�����������������С���ڴ棬�����ڴ治�����˵���OOM���������õĻ������
	 * -Xms5m -Xmx5m -XX:+PrintGCDetails
	 */
	public static void softRef_Memory_NotEnough() {
		Object o1=new Object();
		SoftReference<Object> o2=new SoftReference<Object>(o1);
		System.out.println(o1);
		System.out.println(o2.get());
		System.out.println();
		o1=null;
		
		try {
			byte[] byteArray=new byte[30*1024*1024];
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(o1);
			System.out.println(o2.get());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		softRef_Memory_Enough();
		
		softRef_Memory_NotEnough();
	}

}
