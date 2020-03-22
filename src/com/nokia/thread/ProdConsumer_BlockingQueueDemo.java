package com.nokia.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
	private volatile boolean FLAG=true;
	private AtomicInteger atomicInteger=new AtomicInteger();
	BlockingQueue<String> blockQueue=null;
	//get set��ֵע�룬���߹���ע�룬���ӿ�
	public MyResource(BlockingQueue<String> blockQueue) {
		this.blockQueue=blockQueue;
		System.out.println(blockQueue.getClass().getName());//ͨ��java������Ʋ鿴���崫��ʲôBlockingQueue.
	}
	
	public void myProd() throws Exception {
		String data=null;
		boolean retVal=false;
		while(FLAG) {
			data=atomicInteger.incrementAndGet()+"";			
			retVal=blockQueue.offer(data, 2,TimeUnit.SECONDS);
			if(retVal) {
				System.out.println(Thread.currentThread().getName()+"\t������гɹ�");
			}else {
				System.out.println(Thread.currentThread().getName()+"\t�������ʧ��");				
			}
			
			TimeUnit.SECONDS.sleep(1);			
		}
		
		//falseʱ�˳�
		System.out.println(Thread.currentThread().getName()+"\t���ϰ��ͣ����ʾflagΪfalse��������������");
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
				System.out.println(Thread.currentThread().getName()+"\t����2��û��ȡ���⣬�����˳�");				
				
			}else {				
				System.out.println(Thread.currentThread().getName()+"\t���Ѷ���"+data+"�ɹ�");
			}		
		
	}
}
}
	/**
 * @author yanachen
 * volatile/CAS/atomicInteger/BlockingQueue/�߳̽���
 * while true{
 *   ����һ��������һ��
 * }
 * ֱ��flagΪfalse
 * 
 * ��Ϣ�м���ĵײ�˼��������BlockingQueue������
 * 
 *   ������������ģʽ--V3.0�� BlockingQueue
 * 
 *       �߳�    ������������  ��Դ��
 *       �ж�   �ɻ�                  ֪ͨ
 *       ��ֹ��ٻ��ѻ��� �����̵߳��ж�Ҫ��while,��Ҫ��if��
 */
public class ProdConsumer_BlockingQueueDemo {
	
	public static void main(String[] args) {
		MyResource myResource=new MyResource(new ArrayBlockingQueue<String>(10));	
			
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName()+"�����߳�����");
		
			try {
				myResource.myProd();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "prod").start();
		
		new Thread(() -> {		
			System.out.println(Thread.currentThread().getName()+"�����߳�����");
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
		System.out.println("5����ʱ�䵽�����ϰ��ͣ");
		myResource.stop();
	}
}

