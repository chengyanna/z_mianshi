/**
 * 
 */
package com.nokia.jvm.ref;


/**
 * @author yanachen
 * 强引用，默认支持强引用
 * 死了都不收，即便出现OOM
 *
 */
public class StrongReferenceDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object o1=new Object();
		Object o2=o1; //强引用
		System.out.println(o1);
		System.out.println(o2);
		
		o1=null;
		System.gc();
		System.out.println(o1);
		System.out.println(o2);
		

	}

}
