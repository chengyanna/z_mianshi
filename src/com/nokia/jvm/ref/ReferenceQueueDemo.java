/**
 * 
 */
package com.nokia.jvm.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author yanachen
 * @CreateTime 2020-01-31 12:20:27
 */
public class ReferenceQueueDemo {

	/**
	 * @param args
	 * Reference queues, to which registered reference objects are appended by the
 * garbage collector after the appropriate reachability changes are detected.
	 */
	public static void main(String[] args) {
		Object o1=new Object();
		ReferenceQueue<Object> queue=new ReferenceQueue<Object>();		
		WeakReference<Object> o2=new WeakReference<Object>(o1,queue);
		System.out.println(o1);
		System.out.println(o2.get());		
		System.out.println(queue.poll());
		System.out.println();
		
		o1=null;
		System.gc();
		System.out.println(o1);
		System.out.println(o2.get());
		System.out.println(queue.poll());//GC回收后，将待回收的弱引用放到ReferenceQueue里，java.lang.ref.WeakReference@6d06d69c
	}

}
