/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yanachen
 * 自旋锁是指尝试获取锁的线程不会阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU
 */
public class SpinLockDemo {
	
	AtomicReference<Thread> atomicReference=new AtomicReference<Thread>();//null initial value
	
	public void myLock() {
		System.out.println(Thread.currentThread().getName()+"\t come in!");
		while(!atomicReference.compareAndSet(null, Thread.currentThread())) {
			
		}
	}
	
	public void myUnlock() {
		System.out.println(Thread.currentThread().getName()+"\t invoke myUnlock!");
		atomicReference.compareAndSet(Thread.currentThread(), null);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpinLockDemo spin=new SpinLockDemo();
		new Thread(()->{
			spin.myLock();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			spin.myUnlock();
		},"AA").start();
		
		//确保AA线程先启动
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(()->{
			spin.myLock();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			spin.myUnlock();
		},"BB").start();
		
//		AA	 come in!
//		BB	 come in!
//		AA	 invoke myUnlock!
//		BB	 invoke myUnlock!
//		A线程启动后，首先判断atomicReference.compareAndSet(null, Thread.currentThread())为true，
//		atomicReference的值改为线程AA，B线程启动，尝试获取锁，atomicReference.compareAndSet(null, Thread.currentThread())为false
//		由于A线程抢先获取到锁，所以B线程只能一直循环while，一遍一遍的尝试获取锁，直到AA线程释放锁才能获取锁

	}

}
