/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * @author yanachen
 * ѭ���ϰ���
 *����7����������ٻ�����
 *�˵����˲��ܿ��ᣬǰ���ȵ����˵���
 */
public class CyclicBarrierDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->  {System.out.println("*****�ٻ�����");});
		for (int i = 1; i <= 7; i++) {
			int tempi=i;
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName()+"\t�����"+tempi+"������");
					cyclicBarrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}

	}

}
