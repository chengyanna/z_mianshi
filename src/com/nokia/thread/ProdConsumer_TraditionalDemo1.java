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
 *   ������������ģʽ--V1.0�� sync+wait+notify 
 * ��Ŀ��һ����ʼֵΪ0�ı����������̶߳�����н���������һ����1 һ����1����5��
 * 
 *       �߳�    ������������  ��Դ��
 *       �ж�   �ɻ�                  ֪ͨ
 *       ��ֹ��ٻ��ѻ��� �����̵߳��ж�Ҫ��while,��Ҫ��if��
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
