/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.nokia.enums.CountryEnum;

/**
 * @author yanachen
 * ����������  ǰ��������ɺ��������������񣬱���೤��Ҫ��ͬѧ�Ƕ�����֮���������
 * ʹ��ö�����ͽ�����϶�
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
					System.out.println(Thread.currentThread().getName()+"\t"+CountryEnum.getCountryEnum(tempi).getName()+"����");
					countDownLatch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
		
		//main�߳�һֱwaitֱ�����漸���̶߳�����
//		Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted. 
//		If the current count is zero then this method returns immediately. 
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"\t ��ͳһ����");
		System.out.println();
		System.out.println();
		System.out.println("==========������close door demo============");
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
					System.out.println(Thread.currentThread().getName()+"\t �뿪����");
					countDownLatch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
		
		//main�߳�һֱwaitֱ�����漸���̶߳�����
//		Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted. 
//		If the current count is zero then this method returns immediately. 
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"\t �೤����");
	}

}
