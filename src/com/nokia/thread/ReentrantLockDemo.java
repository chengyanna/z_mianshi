/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanachen
 * ���������������ݹ����� ���������ŵ�Կ���Լ���������Ĺ�ϵ��һ��ֻҪ���˴��ţ������ֱ�ӽ���
 * ָ����ͬһ�߳���㺯�������֮���ڲ�ݹ麯����Ȼ�ܻ�ȡ�����Ĵ��룬
 * ��ͬһ���߳�����㷽���������ʱ���ڽ����ڲ㷽�����Զ���ȡ����
 * Ҳ����˵���߳̿��Խ����κ�һ�����Ѿ�ӵ�е�����ͬ���ŵĴ���顣
 * �����Ǳ�������
 * synchronized��reentrantLock�ǵ��͵Ŀ�������
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
	 * ����lock, unlockҪ�ɶԳ���
	 * ������ɶԳ���,����2��lock,һ��unlock����t3�߳�ִ�к�һֱhang���Ƕ�,t4�߳��޷�ִ�С����󣺽���ʱ����2�����������£�ֻ����һ�������ǳ���ȥ�ģ����ұ���Ҳ�ǽ�������
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
