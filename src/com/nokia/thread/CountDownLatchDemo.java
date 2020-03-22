/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.nokia.enums.CountryEnum;

/**
 * @author yanachen
 * 倒计数门闩  前提任务都完成后才能完成最后的任务，比如班长需要等同学们都走完之后才能锁门
 * 使用枚举类型降低耦合度
 */
public class CountDownLatchDemo {
	private final static int count=6;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CountDownLatch countDownLatch=new CountDownLatch(count);
		
		for (int i = 1; i <= count; i++) {
			final int tempi=i;
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName()+"\t"+CountryEnum.getCountryEnum(tempi).getName()+"灭亡");
					countDownLatch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
		
		//main线程一直wait直到上面几个线程都结束
//		Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted. 
//		If the current count is zero then this method returns immediately. 
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"\t 秦统一天下");
		System.out.println();
		System.out.println();
		System.out.println("==========以下是close door demo============");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		closeDoor();

	}
	
	public static void closeDoor() {
		CountDownLatch countDownLatch=new CountDownLatch(count);
		
		for (int i = 1; i <= count; i++) {
			
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName()+"\t 离开教室");
					countDownLatch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
		
		//main线程一直wait直到上面几个线程都结束
//		Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted. 
//		If the current count is zero then this method returns immediately. 
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"\t 班长锁门");
	}

}
