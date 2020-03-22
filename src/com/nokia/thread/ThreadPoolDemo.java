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
	 * �̳߳صĵײ�ʵ����ThreadPoolExecutor
	 * public ThreadPoolExecutor(int corePoolSize,  --�����߳���
                              int maximumPoolSize,  --�̳߳���������߳���
                              long keepAliveTime,   --�����߳̿���ʱ�䳬��aliveʱ�䣬���𽥱�����ֱ���̳߳���ʣ��corePoolSize���߳�
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue, --�������߳�������������������ʱ��������������������
                              ThreadFactory threadFactory, --�����̵߳Ĺ�����һ�����Ĭ�ϼ���
                              RejectedExecutionHandler handler) --�������߳�����������������Ҳ���������þܾ����ԣ� �ṩ4�оܾ����ԣ�AbortPolicy,DiscardPolicy,CallerRunsPolicy,DiscardOldestPolicy
	 �̳߳صĵײ㹤��ԭ��
	 1.�ڴ������̳߳غ󣬵ȴ��ύ������������
	 2.������excute()�������һ����������ʱ���̳߳ػ��������жϣ�
	   2.1 ����������е��߳���С��corePoolSize,��ô���ϴ����߳���������
	   2.2 ����������е��߳������ڻ��ߵ���corePoolSize,��ô���������������
	   2.3 �����ʱ������������������е��߳�����С��maximumPoolSize,��ô�����Ǻ����߳����������������
	   2.4 ��������������������е��߳������ڻ��ߵ���maximumPoolSize,��ô�̳߳ػ��������;ܾ�������ִ��
	  3.��һ���߳��������ʱ������Ӷ�����ȡ��һ��������ִ��
	  4.��һ���߳����¿�������һ����ʱ�䣨keepAliveTime)ʱ���̳߳ػ��жϣ�
	         �����ǰ���е��߳�������corePoolSize,��ô����̻߳ᱻͣ��
	         
	 ���������̳߳أ�
	 CPU�ܼ���--��Ҫ�������㣬���þ������ٵ��߳���������ʽ��CPU����+1���̵߳��̳߳�
	 IO�ܼ���  --������һֱ��ִ���������þ����ܶ���̣߳�CPU����*2
	        -- CPU����/(1-����ϵ��) ����ϵ����0.8-0.9֮�䣬 ��8�ˣ������� 8/(1-0.8)=40
	 */
	
	public static void main(String[] args) {
//		System.out.println(Runtime.getRuntime().availableProcessors());
//		ExecutorService executorService=Executors.newFixedThreadPool(3);
//		ExecutorService executorService=Executors.newSingleThreadExecutor();
//		ExecutorService executorService=Executors.newCachedThreadPool();
		LinkedBlockingQueue<Runnable> workQueue=new LinkedBlockingQueue<Runnable>(100000);
		ExecutorService executorService=new ThreadPoolExecutor(9, 100, 0L, TimeUnit.MILLISECONDS, workQueue,Executors.defaultThreadFactory(),new AbortPolicy() );
//		ExecutorService executorService=new ThreadPoolExecutor(9, 100, 0L, TimeUnit.MILLISECONDS, workQueue,new SimpleThreadFactory(),new AbortPolicy() );
		
		//���й�̨3�����ڣ�����10����
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
