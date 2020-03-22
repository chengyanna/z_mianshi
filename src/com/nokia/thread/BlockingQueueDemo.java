/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yanachen
 *
 */
public class BlockingQueueDemo {

	/**
	 * @param args
	 * 1.队列
	 * 2.阻塞队列   在多线程领域，所谓阻塞，在某些情况下会挂起线程，一旦条件满足，被挂起的线程优先被唤醒
	 * 首先是一个队列，当队列为空时，从队列中获取元素的操作将被阻塞；当队列已满时，往队列中添加元素的操作将会被阻塞
	 * 	  2.1阻塞队列有没有好的一面
	 *    好处是不用关心什么时候需要阻塞线程，什么时候唤醒线程，因为BlockingQueue都一手给包办了
	 *    2.2不得不阻塞，如果管理？ 医院排队看病，银行排队办业务
	 *    Summary of BlockingQueue methods
             Throws exception Special value    Blocks         Times out 
Insert            add(e)          offer(e)      put(e)         offer(e, time, unit) 
Remove            remove()        poll()        take()         poll(time, unit) 
Examine           element()       peek()        not applicable not applicable 

抛出异常	当阻塞队列满时,再往队列里面add插入元素会抛IllegalStateException: Queue full
当阻塞队列空时,再往队列Remove元素时候回抛出NoSuchElementException
	 * @throws InterruptedException 

	 */
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> blockingQueue=new ArrayBlockingQueue<Integer>(2);
		System.out.println("=============add/remove==================");
		System.out.println(blockingQueue.add(1));
		System.out.println(blockingQueue.add(2));
//		System.out.println(blockingQueue.add(3)); //java.lang.IllegalStateException: Queue full
		
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.remove());
//		System.out.println(blockingQueue.remove());//java.util.NoSuchElementException
		
		System.out.println("=============offer/pull=================");		
		BlockingQueue<Integer> blockingQueue2=new ArrayBlockingQueue<Integer>(2);
		System.out.println(blockingQueue2.offer(1));
		System.out.println(blockingQueue2.offer(2));
		System.out.println(blockingQueue2.offer(3)); //false
		
		System.out.println(blockingQueue2.poll());
		System.out.println(blockingQueue2.poll());
		System.out.println(blockingQueue2.poll());//null
		
		System.out.println("=============offer/pull with timeout=================");		
		BlockingQueue<Integer> blockingQueue3=new ArrayBlockingQueue<Integer>(2);
		System.out.println(blockingQueue3.offer(1,2,TimeUnit.SECONDS));
		System.out.println(blockingQueue3.offer(2,2,TimeUnit.SECONDS));
		System.out.println(blockingQueue3.offer(3,2,TimeUnit.SECONDS)); //等待2秒false
		
		System.out.println(blockingQueue3.poll(2,TimeUnit.SECONDS));
		System.out.println(blockingQueue3.poll(2,TimeUnit.SECONDS));
		System.out.println(blockingQueue3.poll(2,TimeUnit.SECONDS));//等待2秒null
		
		System.out.println("=============take/put=================");		
		BlockingQueue<Integer> blockingQueue4=new ArrayBlockingQueue<Integer>(2);
		blockingQueue4.put(1);// return void
		blockingQueue4.put(2);
//		blockingQueue4.put(3); //blocking
		
		System.out.println(blockingQueue4.take());
		System.out.println(blockingQueue4.take());
//		System.out.println(blockingQueue4.take());//blocking

	}

}
