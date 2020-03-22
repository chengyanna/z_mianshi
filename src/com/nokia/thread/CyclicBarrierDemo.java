/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * @author yanachen
 * 循环障碍点
 *集齐7颗龙珠才能召唤神龙
 *人到齐了才能开会，前面先到的人等着
 */
public class CyclicBarrierDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->  {System.out.println("*****召唤神龙");});
		for (int i = 1; i <= 7; i++) {
			int tempi=i;
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName()+"\t集齐第"+tempi+"颗龙珠");
					cyclicBarrier.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}

	}

}
