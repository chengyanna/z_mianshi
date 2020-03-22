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

		System.out.println("==========������ABA����Ĳ���============");
		new Thread(()->{			
			atomicReference.compareAndSet(100, 101);
			atomicReference.compareAndSet(101, 100);
			
		},"t1").start();
		
		new Thread(()->{
			try {
				Thread.sleep(1000);//��ͣ1����ȷ��t1�߳�ABA���
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName()+"\t"+atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get());
			
		},"t2").start();
		
		System.out.println("==========������ABA����Ľ��============");
		new Thread(()->{	
			int stamp=stampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t ��һ�ΰ汾��"+stamp+"\tֵ��"+stampedReference.getReference());
			try {
				Thread.sleep(1000);//��ͣ1����ȷ��t4�߳�ȡ��ͬ����ֵ
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stampedReference.compareAndSet(100, 101, stamp, stamp+1);
			System.out.println(Thread.currentThread().getName()+"\t �ڶ��ΰ汾��"+stampedReference.getStamp()+"\tֵ��"+stampedReference.getReference());
			stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+"\t �����ΰ汾��"+stampedReference.getStamp()+"\tֵ��"+stampedReference.getReference());
		},"t3").start();
		
		new Thread(()->{
			int stamp=stampedReference.getStamp();
			System.out.println(Thread.currentThread().getName()+"\t ��һ�ΰ汾��"+stamp+"\tֵ��"+stampedReference.getReference());
			try {
				Thread.sleep(3000);//��ͣ3����ȷ��t3�߳�ABA���
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean result=stampedReference.compareAndSet(100, 2019,stamp, stamp+1);
			System.out.println(Thread.currentThread().getName()+"\t �޸ĳɹ����:"+result+"\t���µ�ֵ"+stampedReference.getReference()+"\t���µİ汾��"+stampedReference.getStamp());
			
		},"t4").start();
	
	
	}

}
