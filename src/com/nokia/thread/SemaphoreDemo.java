/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author yanachen
 * Semaphore信号灯，信号量，多个线程抢多个资源
 *   抢车位  30 辆车 抢 20个车位， 没抢到的在外面等着，出去一个进去一个
 * 海底捞等位
 */
public class SemaphoreDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Semaphore semaphore=new Semaphore(3);//模拟3个车位，6辆车
		for (int i = 1; i <= 6; i++) {
			
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName()+"\t停进车位"); //停几秒
					try {
						TimeUnit.SECONDS.sleep(Math.round(Math.random()*10));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"\t驶出，释放停车位");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					semaphore.release();//
				}
			}, String.valueOf(i)).start();
		}

	}

    
}
