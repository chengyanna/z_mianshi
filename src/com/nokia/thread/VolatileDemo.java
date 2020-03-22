package com.nokia.thread;

import java.util.concurrent.atomic.AtomicInteger;

class MyData{
	volatile int number=0;
	public void addTo60(){
		this.number=60;
	}
	public void addPlusPlus() {
		this.number++;
	}
	
	AtomicInteger atomicInteger=new AtomicInteger();
	public void addMyAtomic() {
		atomicInteger.getAndIncrement();
	}
}

public class VolatileDemo {
	

	/**
	 * 1. 验证Volatile可见性
	 * 1.1假如number没有Volatile关键字，没有可见性，最后一句话始终打印不输出
	 * 1.2如果number前加上了volatile关键字，Main线程能及时获知AAA线程修改后的值，可见性,最后一句话可以打印出来
	 * 
	 * 2.验证volatile不保证原子性
	 *  2.1何为原子性？数据完整性，一致性，线程不被加塞或者分隔，要么全部成功，要么全部失败
	 *  2.2不保证原子性案例
	 *  2.3 why number++是线程不安全的，有可能写覆盖， number++实际上三步操作，a)get field取值 b)number+1 3)put field写回主内存, 当一个线程前两步完成，正准备put field时，线程被挂起，此时另外一个线程修改了number的值，然后没来及获取最新的值就put field了
	 *  2.4 如何解决 a)加synchronized b)使用juc下的AtomicInteger 底层实现原理是CAS
	 */
	public static void main(String[] args) {
		MyData myData=new MyData();
		
		//启动20个线程，每个线程i++ 1000次, 理论number的值应该为20000，但实际上每次执行结果可能都不同
		for(int i=1;i<=20;i++) {
			for(int j=1;j<=1000;j++) {
				
				new Thread(()->{
					myData.addPlusPlus();
					myData.addMyAtomic();
					
				},String.valueOf(i)).start();
			}
		}
		
		//需要等待上面20个线程全部计算完成后，再用main线程取得最终的计算结果值
		while(Thread.activeCount()>2) {
			Thread.yield();//线程退出,放弃占用CPU
		}
		System.out.println("int final value is :"+myData.number);
		System.out.println("AtomicInteger final value is :"+myData.atomicInteger);
		

	}
	//验证可见性案例
	public static void seeOkByVolatile() {
		MyData myData=new MyData();
		
		//线程AAA
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" come in");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myData.addTo60();
			System.out.println(Thread.currentThread().getName()+" updated number value:"+myData.number);
			
		},"AAA").start();
		
		//main主线程
		while(myData.number==0) {
			//只要为0就一直等待
		}
		System.out.println(Thread.currentThread().getName()+" mission is over!main get number value:"+myData.number);
		
		
	}

}
