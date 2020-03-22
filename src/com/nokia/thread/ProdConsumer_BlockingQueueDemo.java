package com.nokia.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
	private volatile boolean FLAG=true;
	private AtomicInteger atomicInteger=new AtomicInteger();
	BlockingQueue<String> blockQueue=null;
	//get set赋值注入，或者构造注入，传接口
	public MyResource(BlockingQueue<String> blockQueue) {
		this.blockQueue=blockQueue;
		System.out.println(blockQueue.getClass().getName());//通过java反射机制查看具体传入什么BlockingQueue.
	}
	
	public void myProd() throws Exception {
		String data=null;
		boolean retVal=false;
		while(FLAG) {
			data=atomicInteger.incrementAndGet()+"";			
			retVal=blockQueue.offer(data, 2,TimeUnit.SECONDS);
			if(retVal) {
				System.out.println(Thread.currentThread().getName()+"\t插入队列成功");
			}else {
				System.out.println(Thread.currentThread().getName()+"\t插入队列失败");				
			}
			
			TimeUnit.SECONDS.sleep(1);			
		}
		
		//false时退出
		System.out.println(Thread.currentThread().getName()+"\t大老板叫停，表示flag为false，生产动作结束");
	}
	
	public void stop(){
		this.FLAG=false;
	}
	
	public void myConsume() throws Exception {
		String data=null;
		while(FLAG) {
			data=blockQueue.poll(2L,TimeUnit.SECONDS);	
			if(data==null ||data.equalsIgnoreCase("")) {
				FLAG=false;				
				System.out.println(Thread.currentThread().getName()+"\t超过2秒没有取蛋糕，消费退出");				
				
			}else {				
				System.out.println(Thread.currentThread().getName()+"\t消费队列"+data+"成功");
			}		
		
	}
}
}
	/**
 * @author yanachen
 * volatile/CAS/atomicInteger/BlockingQueue/线程交互
 * while true{
 *   生产一个，消费一个
 * }
 * 直到flag为false
 * 
 * 消息中间件的底层思想就是这个BlockingQueue。。。
 * 
 *   生产者消费者模式--V3.0版 BlockingQueue
 * 
 *       线程    操作（方法）  资源类
 *       判断   干活                  通知
 *       防止虚假唤醒机制 （多线程的判断要用while,不要用if）
 */
public class ProdConsumer_BlockingQueueDemo {
	
	public static void main(String[] args) {
		MyResource myResource=new MyResource(new ArrayBlockingQueue<String>(10));	
			
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName()+"生产线程启动");
		
			try {
				myResource.myProd();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "prod").start();
		
		new Thread(() -> {		
			System.out.println(Thread.currentThread().getName()+"消费线程启动");
			System.out.println();
			System.out.println();
			try {
				myResource.myConsume();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "consume").start();			
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("5秒钟时间到，大老板叫停");
		myResource.stop();
	}
}

