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
 * 1.从AtomicIntegerFieldUpdaterDemo代码中我们不难发现，通过AtomicIntegerFieldUpdater更新score我们获取最后的int值时相较于AtomicInteger来说不需要调用get()方法！
2.对于AtomicIntegerFieldUpdaterDemo类的AtomicIntegerFieldUpdater是static final类型也就是说即使创建了100个对象AtomicIntegerField也只存在一个不会占用对象的内存，
但是AtomicInteger会创建多个AtomicInteger对象，占用的内存比AtomicIntegerFieldUpdater大，
所以对于熟悉dubbo源码的人都知道，dubbo有个实现轮询负载均衡策略的类AtomicPositiveInteger用的就是AtomicIntegerFieldUpdater。

 * AtomicXXXFieldUpdater，就是可以以一种线程安全的方式操作非线程安全对象的某些字段。
举例：1000个人同时向一个账号转账一元钱，那么累计应该增加1000元，除了锁和AtomicInteger还可以使用FieldUpdater来实现
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
					// 通过AtomicIntegerFieldUpdater操作字段
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
