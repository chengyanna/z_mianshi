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
			//�ж�
			while(number!=0) {
				condition.await();
			}
			//�ɻ�
			number++;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//֪ͨ
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
			//�ж�
			while(number==0) {
				condition.await();
			}
			//�ɻ�
			number--;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			//����
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
 *   ������������ģʽ---V2.0�� Lock + await + signal
 * ��Ŀ��һ����ʼֵΪ0�ı����������̶߳�����н���������һ����1 һ����1����5��
 * 
 *       �߳�    ������������  ��Դ��
 *       �ж�   �ɻ�                  ֪ͨ
 *       ��ֹ��ٻ��ѻ��� �����̵߳��ж�Ҫ��while,��Ҫ��if��
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
