/**
 * 
 */
package com.nokia.jvm.ref;


/**
 * @author yanachen
 * ǿ���ã�Ĭ��֧��ǿ����
 * ���˶����գ��������OOM
 *
 */
public class StrongReferenceDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object o1=new Object();
		Object o2=o1; //ǿ����
		System.out.println(o1);
		System.out.println(o2);
		
		o1=null;
		System.gc();
		System.out.println(o1);
		System.out.println(o2);
		

	}

}
