/**
 * 
 */
package com.nokia.jvm.ref;

import java.lang.ref.WeakReference;

/**
 * @author yanachen
 * ����ֻ�������õĶ�����˵��ֻҪGCһ���оͻ��ոö���ռ�õ��ڴ棬������JVM���ڴ�ռ乻����
 *
 */
public class WeakReferenceDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object o1=new Object();
		WeakReference<Object> o2=new WeakReference<Object>(o1);
		System.out.println(o1);
		System.out.println(o2.get());
		
		o1=null;
		System.gc();
		System.out.println(o1);
		System.out.println(o2.get());

	}

}
