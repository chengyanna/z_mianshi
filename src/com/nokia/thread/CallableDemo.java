/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName()+"\t come in callable");
		return 1024;
	}
	
}
/**
 * @author yanachen
 * before: ���Ǻ�«����һ����һ��˳��ִ�� ����һ����Ҫ1�룬�ڶ�����Ҫ2�룬��������Ҫ10�룬���ĸ���Ҫ1�룬Ҫִ�е��ĸ�1�룬��Ҫ������ִ����ϣ�֮ǰһֱ���������ȴ�״̬
 * after: ��֧�ϲ�����ִ��ʱ��ϳ��ĵ��������������������FutureTask, һ�������꣬������֮���ٻ���
 *
 */
public class CallableDemo {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask=new FutureTask<Integer>(new MyThread());
		
		Thread t1=new Thread(futureTask,"AA");
		t1.start();
		Thread t2=new Thread(futureTask,"BB");//��Ȼ����AA��BB�����̣߳�����callable����ִֻ����һ�Σ���FutureTaskִֻ��һ�Σ������Ҫִ�У���ônew ���Future Task
		t2.start();
		
		while(!futureTask.isDone()) {//û��ɾ͵���,������ѡ��
			
		}
		Integer result=futureTask.get();//������ڿ����λ�ã���Ϊ��һֱ�ȴ�ֱ���������Waits if necessary for the computation to complete, and then retrieves its result.
		Integer init=20;
		System.out.println("final result is "+(result+init));

	}

}
