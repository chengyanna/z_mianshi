/**
 * 
 */
package com.nokia.jvm.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author yanachen
 * @CreateTime 2020-01-31 12:40:04
 * java提供了4种引用类型，在垃圾回收的时候，都有自己各自的特点
 * ReferenceQueue是用来配合引用工作的，没有ReferenceQueue一样可以运行。
 * 
 * 创建引用的时候可以指定关联的队列，当GC释放对象内存的时候，会将引用加入到引用队列。
 * 如果程序发现某个虚引用已经被加入到引用队列，那么可以在所引用的对象的内存回收之前采取必要的行动。
 * 这相当于一种通知机制。类似于AOP的后置通知
 * 
 * 当关联的引用队列中有数据的时候，意味着引用指向的堆内存中的对象被回收。通过这种方式，JVM允许我们在对象被销毁后，做一些我们自己想做的事情。
 * 
 * 如果一个对象仅持有虚引用，那么他就如同没有任何引用一样，随时都可以被垃圾回收。
 * 虚引用必须和引用队列联合使用
 * 虚引用的主要作用是跟踪对象被垃圾回收的状态，PhantomReference的get方法总是返回null
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
		System.out.println(o2.get());//总是null	
		System.out.println(queue.poll());
		System.out.println();
		
		o1=null;
		System.gc();
		System.out.println(o1);
		System.out.println(o2.get());//总是null
		System.out.println(queue.poll());//GC回收后，将待回收的弱引用放到ReferenceQueue里，java.lang.ref.PhantomReference@6d06d69c
	}

}
