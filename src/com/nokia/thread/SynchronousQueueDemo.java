/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yanachen
 * SynchronousQueueû������
������BlcokingQueue��ͬ,SynchronousQueue��һ�����洢Ԫ�ص�BlockingQueue
ÿ��put��������Ҫ�ȴ�һ��take����,�����ܼ������Ԫ��,��֮��Ȼ.
 *
 */
public class SynchronousQueueDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BlockingQueue<Integer> queue=new SynchronousQueue<Integer>();
		new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName()+"\t put 1");
				queue.put(1);
				System.out.println(Thread.currentThread().getName()+"\t put 2");
				queue.put(2);
				System.out.println(Thread.currentThread().getName()+"\t put 3");
				queue.put(3);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "AA").start();
		
		new Thread(() -> {
			try {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"\t take "+queue.take());
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"\t take "+queue.take());
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"\t take "+queue.take());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "BB").start();
		
	}

}
