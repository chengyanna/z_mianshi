package com.nokia.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanachen
 * ��Ŀ��synchronized��Lock��ʲô�������µ�Lock��ʲô�ô�������˵˵
 * 1.ԭʼ����
 *   synchronized�ǹؼ��֣�����JVM����
 *      monitorenter(�ײ���ͨ��monitor������ɣ���ʵwait/notify����Ҳ������monitor����ֻ����ͬ�������ͬ�������в��ܵ���wait/notify�ȷ���)
 *      monitorexit
 *   Lock�Ǿ����ࣨjava.util.concurrent.lock.Lock����API�������
 * 2.ʹ�÷���
 *   synchronized����Ҫ�û��ֶ��ͷ�������synchronized����ִ����ɺ�ϵͳ�����߳��Զ��ͷŶ�����ռ��
 *   ReentrantLock��Ҫ�û��ֶ��ͷ�������û�������ͷ������п��ܳ�������������Ҫlock unlock�������try finally���������
 * 3.�ȴ��Ƿ���ж�
 *   synchronized�����жϣ������׳��쳣���������������
 *   ReentrantLock���жϣ�1.���ó�ʱ����lock.tryLock(long timeout, TimeUnit unit) 2.lock.lockInterruptibly() ����interrupt()�������ж�
 * 4.�����Ƿ�ƽ
 *   synchronized �ǹ�ƽ��
 *   ReentrantLock���߶����ԣ�Ĭ�Ϸǹ�ƽ�������췽�����Դ���booleanֵ��trueΪ��ƽ����falseΪ�ǹ�ƽ��
 * 5.���󶨶������
 *   synchronizedû��
 *   ReentrantLock����ʵ�ַ��黽����Ҫ���ѵ��߳��ǣ����Ծ�ȷ���ѣ���������synchronizedҪô����һ���߳�Ҫô����ȫ���߳� 
 *   
 *   ================================================================================
 *   ================================================================================
 *   ================================================================================
 *   ��Ŀ�����߳�֮�䰴˳����ã�ʵ��A->B->C�����߳�������Ҫ�����£�
 *   AA��ӡ5�Σ�BB��ӡ10�Σ�CC��ӡ15��
 *   a������
 *   AA��ӡ5�Σ�BB��ӡ10�Σ�CC��ӡ15��
 *         �� 10��
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
