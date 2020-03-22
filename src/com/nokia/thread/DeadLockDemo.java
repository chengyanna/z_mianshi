/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author yanachen
 * 死锁是两个或者两个以上的进程在执行过程中因争夺资源造成的一种相互等待现象，若无外力干涉他们都将无法推进下去
 * 产生死锁的主要原因：1.系统资源不足 2.进行运行推进的顺序不合适 3.资源分配不当
 * 
 * 定位分析  jvm process status
 * jps -l 查看java进程编号
 * jstack -l pid 查看具体进程的堆栈信息
 *
 */
class HoldThread implements Runnable{
	private String lockA;
	private String lockB;
	

	public HoldThread(String lockA, String lockB) {
		super();
		this.lockA = lockA;
		this.lockB = lockB;
	}


	@Override
	public void run() {
		synchronized(lockA) {
			System.out.println(Thread.currentThread().getName()+"\t 自己持有锁"+lockA+"尝试获得锁"+lockB);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(lockB) {
				System.out.println(Thread.currentThread().getName()+"\t 自己持有锁"+lockB+"尝试获得锁"+lockA);
			}
		}
		
	}
	
}
public class DeadLockDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String lockA="lockA";
		String lockB="lockB";
		new Thread(new HoldThread(lockA,lockB)).start();
		new Thread(new HoldThread(lockB,lockA)).start();

	}

}
