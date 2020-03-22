/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yanachen
 *
 */
public class ABADemo {
	static AtomicReference<Integer> atomicReference=new AtomicReference<Integer>(100);
	static AtomicStampedReference<Integer> stampedReference=new AtomicStampedReference<Integer>(100,1);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("==========以下是ABA问题的产生============");
		new Thread(()->{			
			atomicReference.compareAndSet(100, 101);
			atomicReference.compareAndSet(101, 100);
			
		},"t1").start();
		
		new Thread(()->{
			try {
				Thread.sleep(1000);//暂停1秒钟确保t1线程ABA完成
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName()+"\t"+atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get());
			
		},"t2").start();
		
		System.out.println("==========以下是ABA问题的解决============");
		new Thread(()->{	
			int stamp=stampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t 第一次版本号"+stamp+"\t值是"+stampedReference.getReference());
			try {
				Thread.sleep(1000);//暂停1秒钟确保t4线程取到同样的值
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stampedReference.compareAndSet(100, 101, stamp, stamp+1);
			System.out.println(Thread.currentThread().getName()+"\t 第二次版本号"+stampedReference.getStamp()+"\t值是"+stampedReference.getReference());
			stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t 第三次版本号"+stampedReference.getStamp()+"\t值是"+stampedReference.getReference());
		},"t3").start();
		
		new Thread(()->{
			int stamp=stampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t 第一次版本号"+stamp+"\t值是"+stampedReference.getReference());
			try {
				Thread.sleep(3000);//暂停3秒钟确保t3线程ABA完成
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean result=stampedReference.compareAndSet(100, 2019,stamp, stamp+1);
			System.out.println(Thread.currentThread().getName()+"\t 修改成功与否:"+result+"\t最新的值"+stampedReference.getReference()+"\t最新的版本号"+stampedReference.getStamp());
			
		},"t4").start();
	
	
	}

}
