/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yanachen
 * ��������ָ���Ի�ȡ�����̲߳������������ǲ���ѭ���ķ�ʽȥ���Ի�ȡ���������ĺô��Ǽ����߳��������л������ģ�ȱ����ѭ��������CPU
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
		
		//ȷ��AA�߳�������
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
//		A�߳������������ж�atomicReference.compareAndSet(null, Thread.currentThread())Ϊtrue��
//		atomicReference��ֵ��Ϊ�߳�AA��B�߳����������Ի�ȡ����atomicReference.compareAndSet(null, Thread.currentThread())Ϊfalse
//		����A�߳����Ȼ�ȡ����������B�߳�ֻ��һֱѭ��while��һ��һ��ĳ��Ի�ȡ����ֱ��AA�߳��ͷ������ܻ�ȡ��

	}

}
