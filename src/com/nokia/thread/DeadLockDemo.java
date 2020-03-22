/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author yanachen
 * ���������������������ϵĽ�����ִ�й�������������Դ��ɵ�һ���໥�ȴ��������������������Ƕ����޷��ƽ���ȥ
 * ������������Ҫԭ��1.ϵͳ��Դ���� 2.���������ƽ���˳�򲻺��� 3.��Դ���䲻��
 * 
 * ��λ����  jvm process status
 * jps -l �鿴java���̱��
 * jstack -l pid �鿴������̵Ķ�ջ��Ϣ
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
			System.out.println(Thread.currentThread().getName()+"\t �Լ�������"+lockA+"���Ի����"+lockB);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(lockB) {
				System.out.println(Thread.currentThread().getName()+"\t �Լ�������"+lockB+"���Ի����"+lockA);
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
