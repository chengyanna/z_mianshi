/**
 * 
 */
package com.nokia.jvm.ref;

import java.lang.ref.WeakReference;

/**
 * @author yanachen
 * 对于只有弱引用的对象来说，只要GC一运行就回收该对象占用的内存，而不管JVM的内存空间够不够
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
