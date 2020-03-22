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
	 * 1.����
	 * 2.��������   �ڶ��߳�������ν��������ĳЩ����»�����̣߳�һ���������㣬��������߳����ȱ�����
	 * ������һ�����У�������Ϊ��ʱ���Ӷ����л�ȡԪ�صĲ�����������������������ʱ�������������Ԫ�صĲ������ᱻ����
	 * 	  2.1����������û�кõ�һ��
	 *    �ô��ǲ��ù���ʲôʱ����Ҫ�����̣߳�ʲôʱ�����̣߳���ΪBlockingQueue��һ�ָ�������
	 *    2.2���ò�������������� ҽԺ�Ŷӿ����������ŶӰ�ҵ��
	 *    Summary of BlockingQueue methods
             Throws exception Special value    Blocks         Times out 
Insert            add(e)          offer(e)      put(e)         offer(e, time, unit) 
Remove            remove()        poll()        take()         poll(time, unit) 
Examine           element()       peek()        not applicable not applicable 

�׳��쳣	������������ʱ,������������add����Ԫ�ػ���IllegalStateException: Queue full
���������п�ʱ,��������RemoveԪ��ʱ����׳�NoSuchElementException
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
		System.out.println(blockingQueue3.offer(3,2,TimeUnit.SECONDS)); //�ȴ�2��false
		
		System.out.println(blockingQueue3.poll(2,TimeUnit.SECONDS));
		System.out.println(blockingQueue3.poll(2,TimeUnit.SECONDS));
		System.out.println(blockingQueue3.poll(2,TimeUnit.SECONDS));//�ȴ�2��null
		
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
