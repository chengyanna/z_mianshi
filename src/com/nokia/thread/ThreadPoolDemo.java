/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanachen
 *
 */
class MyTask implements Runnable{
	 
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"\t runnable job");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class MyTask2 implements Callable<Integer>{	
	private String url;	

	public MyTask2(String url) {
		super();
		this.url = url;
	}

	@Override
	public Integer call() throws Exception {
		
		System.out.println(Thread.currentThread().getName()+"\t callable job \t"+url);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 1024;
	}
	
}

class SimpleThreadFactory implements ThreadFactory {
	private final AtomicInteger threadNumber = new AtomicInteger(1);

	public Thread newThread(Runnable r) {
		return new Thread(r,"AA-"+ threadNumber.getAndIncrement());
	}
}
	
public class ThreadPoolDemo {
	
	

	/**
	 * @param args
	 * 线程池的底层实现是ThreadPoolExecutor
	 * public ThreadPoolExecutor(int corePoolSize,  --核心线程数
                              int maximumPoolSize,  --线程池中最大并行线程数
                              long keepAliveTime,   --空闲线程空闲时间超过alive时间，会逐渐被销毁直至线程池中剩下corePoolSize个线程
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue, --当核心线程数已满，又来新任务时，任务存放在阻塞队列里
                              ThreadFactory threadFactory, --创建线程的工厂，一般采用默认即可
                              RejectedExecutionHandler handler) --当核心线程已满，且阻塞队列也已满，启用拒绝策略， 提供4中拒绝策略：AbortPolicy,DiscardPolicy,CallerRunsPolicy,DiscardOldestPolicy
	 线程池的底层工作原理：
	 1.在创建了线程池后，等待提交过来的新任务
	 2.当调用excute()方法添加一个请求任务时，线程池会做如下判断：
	   2.1 如果正在运行的线程数小于corePoolSize,那么马上创建线程启动任务
	   2.2 如果正在运行的线程数大于或者等于corePoolSize,那么将这个任务放入队列
	   2.3 如果这时候队列满了且正在运行的线程数量小于maximumPoolSize,那么创建非核心线程立刻运行这个任务
	   2.4 如果队列满了且正在运行的线程数大于或者等于maximumPoolSize,那么线程池会启动饱和拒绝策略来执行
	  3.当一个线程完成任务时，它会从队列中取下一个任务来执行
	  4.当一个线程无事可做超过一定的时间（keepAliveTime)时，线程池会判断：
	         如果当前运行的线程数大于corePoolSize,那么这个线程会被停掉
	         
	 合理配置线程池：
	 CPU密集型--需要大量运算，配置尽可能少的线程数量，公式：CPU核数+1个线程的线程池
	 IO密集型  --并不是一直在执行任务，配置尽可能多的线程，CPU核数*2
	        -- CPU核数/(1-阻塞系数) 阻塞系数在0.8-0.9之间， 如8核，则配置 8/(1-0.8)=40
	 */
	
	public static void main(String[] args) {
//		System.out.println(Runtime.getRuntime().availableProcessors());
//		ExecutorService executorService=Executors.newFixedThreadPool(3);
//		ExecutorService executorService=Executors.newSingleThreadExecutor();
//		ExecutorService executorService=Executors.newCachedThreadPool();
		LinkedBlockingQueue<Runnable> workQueue=new LinkedBlockingQueue<Runnable>(100000);
		ExecutorService executorService=new ThreadPoolExecutor(9, 100, 0L, TimeUnit.MILLISECONDS, workQueue,Executors.defaultThreadFactory(),new AbortPolicy() );
//		ExecutorService executorService=new ThreadPoolExecutor(9, 100, 0L, TimeUnit.MILLISECONDS, workQueue,new SimpleThreadFactory(),new AbortPolicy() );
		
		//银行柜台3个窗口，来了10个人
		try {
//			for (int i = 1; i < 20; i++) {
//				executorService.execute(new MyTask());//pool-1-thread-1
//			}
			Future<Integer> task1=executorService.submit(new MyTask2("10.13.14.15"));
			Future<Integer> task2=executorService.submit(new MyTask2("20.22.23.24"));
//			while(!task.isDone()) {
//				
//			}
//			try {
//				System.out.println(task.get());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
		} finally {
			executorService.shutdown();
		}
		
		
		
	
		
		

	}

}
