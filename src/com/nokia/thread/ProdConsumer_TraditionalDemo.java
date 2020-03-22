package com.nokia.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
	private int number=0;
	
	Lock lock=new ReentrantLock();
	Condition condition=lock.newCondition();
	
	public void increment(){
		lock.lock();
		try {
			//判断
			while(number!=0) {
				condition.await();
			}
			//干活
			number++;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//通知
			condition.signalAll();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}		
	}
	
	public void decrement() {
		lock.lock();
		try {
			//判断
			while(number==0) {
				condition.await();
			}
			//干活
			number--;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//唤醒
			condition.signalAll();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}		
	}
}

/**
 * @author yanachen
 *   生产者消费者模式---V2.0版 Lock + await + signal
 * 题目：一个初始值为0的变量，两个线程对其进行交互操作，一个加1 一个减1，来5轮
 * 
 *       线程    操作（方法）  资源类
 *       判断   干活                  通知
 *       防止虚假唤醒机制 （多线程的判断要用while,不要用if）
 */
public class ProdConsumer_TraditionalDemo {
	
	public static void main(String[] args) {
		ShareData shareData=new ShareData();		
			
		new Thread(() -> {
			try {
				for (int i = 1; i <=5; i++) {
					shareData.increment();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "AA").start();
		
		new Thread(() -> {
			try {
				for (int i = 1; i <= 5; i++) {
					shareData.decrement();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "BB").start();	
		
		new Thread(() -> {
			try {
				for (int i = 1; i <=5; i++) {
					shareData.increment();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "CC").start();
		
		new Thread(() -> {
			try {
				for (int i = 1; i <= 5; i++) {
					shareData.decrement();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "DD").start();
		
		

	}

}
