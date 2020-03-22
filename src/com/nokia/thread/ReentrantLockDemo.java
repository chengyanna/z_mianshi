/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanachen
 * 可重入锁（又名递归锁） 想象家里大门的钥匙以及各个房间的关系，一般只要开了大门，房间就直接进入
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一个线程在外层方法获得锁的时候，在进入内层方法会自动获取锁。
 * 也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块。
 * 作用是避免死锁
 * synchronized和reentrantLock是典型的可重入锁
 */
class Phone{
	public synchronized void sendSMS() throws Exception{
		try {			
			System.out.println(Thread.currentThread().getName()+"\t sendSMS invoked");
			sendEmail();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void sendEmail() throws Exception{
		try {		
			System.out.println(Thread.currentThread().getName()+"\t #######sendEmail invoked");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	Lock lock=new ReentrantLock();
	/**
	 * 锁的lock, unlock要成对出现
	 * 如果不成对出现,例如2个lock,一个unlock，则t3线程执行后一直hang在那儿,t4线程无法执行。想象：进屋时加了2把锁，干完事，只开了一把锁，是出不去的，而且别人也是进不来的
	 */
	public void get() {
		lock.lock();
//		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t get invoked");
			set();
		}finally {
			lock.unlock();
//			lock.unlock();
		}
	}
	
	public void set() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t ######set invoked");
		}finally {
			lock.unlock();
		}
	}
}
public class ReentrantLockDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Phone phone=new Phone();
		new Thread(()->{
			try {
				phone.sendSMS();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"t1").start();
		
		new Thread(()->{
			try {
				phone.sendSMS();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"t2").start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println();
		System.out.println();
		
		new Thread(()->{
			try {
				phone.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"t3").start();
		
		new Thread(()->{
			try {
				phone.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"t4").start();


	}

}
