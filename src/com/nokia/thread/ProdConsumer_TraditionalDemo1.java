package com.nokia.thread;

class ShareData1{
	private int number=0;	
	
	public synchronized void increment(){
		
		try {
			while(number!=0) {
				this.wait();
			}
			number++;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			this.notifyAll();
		}catch (Exception e) {
			e.printStackTrace();
		}	
			
	}
	
	public synchronized void decrement() {		
		try {
			while(number==0) {
				this.wait();
			}
			number--;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			this.notifyAll();
		}catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
}

/**
 * @author yanachen
 *   生产者消费者模式--V1.0版 sync+wait+notify 
 * 题目：一个初始值为0的变量，两个线程对其进行交互操作，一个加1 一个减1，来5轮
 * 
 *       线程    操作（方法）  资源类
 *       判断   干活                  通知
 *       防止虚假唤醒机制 （多线程的判断要用while,不要用if）
 */
public class ProdConsumer_TraditionalDemo1 {
	
	public static void main(String[] args) {
		ShareData1 shareData=new ShareData1();		
			
		new Thread(() -> {
			try {
				for (int i = 1; i <=5; i++) {
					shareData.increment();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "A").start();
		
		new Thread(() -> {
			try {
				for (int i = 1; i <= 5; i++) {
					shareData.decrement();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "B").start();
		
		new Thread(() -> {
			try {
				for (int i = 1; i <=5; i++) {
					shareData.increment();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "C").start();
		
		new Thread(() -> {
			try {
				for (int i = 1; i <= 5; i++) {
					shareData.decrement();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "D").start();

	}

}
