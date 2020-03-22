/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author yanachen
 * Semaphore�źŵƣ��ź���������߳��������Դ
 *   ����λ  30 ���� �� 20����λ�� û��������������ţ���ȥһ����ȥһ��
 * �����̵�λ
 */
public class SemaphoreDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Semaphore semaphore=new Semaphore(3);//ģ��3����λ��6����
		for (int i = 1; i <= 6; i++) {
			
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName()+"\tͣ����λ"); //ͣ����
					try {
						TimeUnit.SECONDS.sleep(Math.round(Math.random()*10));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"\tʻ�����ͷ�ͣ��λ");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					semaphore.release();//
				}
			}, String.valueOf(i)).start();
		}

	}

    
}
