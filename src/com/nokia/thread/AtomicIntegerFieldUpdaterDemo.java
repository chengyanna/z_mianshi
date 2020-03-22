/**
 * 
 */
package com.nokia.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author yanachen
 * 
 * 1.��AtomicIntegerFieldUpdaterDemo���������ǲ��ѷ��֣�ͨ��AtomicIntegerFieldUpdater����score���ǻ�ȡ����intֵʱ�����AtomicInteger��˵����Ҫ����get()������
2.����AtomicIntegerFieldUpdaterDemo���AtomicIntegerFieldUpdater��static final����Ҳ����˵��ʹ������100������AtomicIntegerFieldҲֻ����һ������ռ�ö�����ڴ棬
����AtomicInteger�ᴴ�����AtomicInteger����ռ�õ��ڴ��AtomicIntegerFieldUpdater��
���Զ�����ϤdubboԴ����˶�֪����dubbo�и�ʵ����ѯ���ؾ�����Ե���AtomicPositiveInteger�õľ���AtomicIntegerFieldUpdater��

 * AtomicXXXFieldUpdater�����ǿ�����һ���̰߳�ȫ�ķ�ʽ�������̰߳�ȫ�����ĳЩ�ֶΡ�
������1000����ͬʱ��һ���˺�ת��һԪǮ����ô�ۼ�Ӧ������1000Ԫ����������AtomicInteger������ʹ��FieldUpdater��ʵ��
 *
 */
class BankAccount{
	volatile int money;
	
	public BankAccount(int money) {
		super();
		this.money = money;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
}
public class AtomicIntegerFieldUpdaterDemo {
	private static AtomicIntegerFieldUpdater<BankAccount> updater=AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BankAccount account=new BankAccount(0);		
		
		for (int i = 1; i <= 10; i++) {
			
			new Thread(() -> {
				try {
					// ͨ��AtomicIntegerFieldUpdater�����ֶ�
					AtomicIntegerFieldUpdaterDemo.updater.addAndGet(account, 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();			
		}
		
		//or thread.join()
		while(Thread.activeCount()>1) {
			
		}
        System.out.println(account.getMoney());

	}

}
