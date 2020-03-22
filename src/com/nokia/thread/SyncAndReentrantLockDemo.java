package com.nokia.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanachen
 * 题目：synchronized和Lock有什么区别？用新的Lock有什么好处？举例说说
 * 1.原始构成
 *   synchronized是关键字，属于JVM层面
 *      monitorenter(底层是通过monitor对象完成，其实wait/notify方法也依赖于monitor对象，只有在同步块或者同步方法中才能调用wait/notify等方法)
 *      monitorexit
 *   Lock是具体类（java.util.concurrent.lock.Lock）是API层面的锁
 * 2.使用方法
 *   synchronized不需要用户手动释放锁，当synchronized代码执行完成后系统会让线程自动释放对锁的占用
 *   ReentrantLock需要用户手动释放锁，若没有主动释放锁，有可能出现死锁现象，需要lock unlock方法配合try finally语句块来完成
 * 3.等待是否可中断
 *   synchronized不可中断，除非抛出异常或者正常运行完成
 *   ReentrantLock可中断，1.设置超时方法lock.tryLock(long timeout, TimeUnit unit) 2.lock.lockInterruptibly() 调用interrupt()方法可中断
 * 4.加锁是否公平
 *   synchronized 非公平锁
 *   ReentrantLock两者都可以，默认非公平锁，构造方法可以传入boolean值，true为公平锁，false为非公平锁
 * 5.锁绑定多个条件
 *   synchronized没有
 *   ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么唤醒一个线程要么唤醒全部线程 
 *   
 *   ================================================================================
 *   ================================================================================
 *   ================================================================================
 *   题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 *   AA打印5次，BB打印10次，CC打印15次
 *   a紧接着
 *   AA打印5次，BB打印10次，CC打印15次
 *         来 10轮
 */
class MyData2{
	private volatile int FLAG=1; //1:A  2:B 3:C
	Lock lock=new ReentrantLock();
	Condition condA=lock.newCondition();
	Condition condB=lock.newCondition();
	Condition condC=lock.newCondition();
	
	public void print5() throws InterruptedException {
		lock.lock();
		try {
			while(FLAG!=1) {
				condA.await();				
			}
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t print"+i);
			}			
			FLAG=2;
			condB.signal();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}		
	}
	
	public void print10() throws InterruptedException {
		lock.lock();
		try {
			while(FLAG!=2) {				
				condB.await();
			}
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName()+"\t print"+i);
			}			
			FLAG=3;
			condC.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}			
	}
	
	public void print15() throws InterruptedException {
		lock.lock();
		try {
			while(FLAG!=3) {				
				condC.await();			
			}
			for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName()+"\t print"+i);
			}	
			FLAG=1;
			condA.signal();
			System.out.println();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
}
public class SyncAndReentrantLockDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyData2 mydata=new MyData2();
		
		new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {					
					mydata.print5();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "AA").start();
		
		new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {					
					mydata.print10();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "BB").start();
		
		new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {					
					mydata.print15();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "CC").start();

	}

}
