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
 * before: 冰糖葫芦串，一个接一个顺序执行 ，第一个需要1秒，第二个需要2秒，第三个需要10秒，第四个需要1秒，要执行第四个1秒，需要第三个执行完毕，之前一直处于阻塞等待状态
 * after: 分支合并，将执行时间较长的第三个单独拎出来，做成FutureTask, 一二四算完，三算完之后再汇总
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
		Thread t2=new Thread(futureTask,"BB");//虽然起了AA和BB两个线程，但是callable方法只执行了一次，即FutureTask只执行一次，如果非要执行，那么new 多个Future Task
		t2.start();
		
		while(!futureTask.isDone()) {//没完成就等着,类似自选锁
			
		}
		Integer result=futureTask.get();//建议放在靠后的位置，因为会一直等待直到计算完成Waits if necessary for the computation to complete, and then retrieves its result.
		Integer init=20;
		System.out.println("final result is "+(result+init));

	}

}
