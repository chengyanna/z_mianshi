/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yanachen
 * SynchronousQueue没有容量
与其他BlcokingQueue不同,SynchronousQueue是一个不存储元素的BlockingQueue
每个put操作必须要等待一个take操作,否则不能继续添加元素,反之亦然.
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
