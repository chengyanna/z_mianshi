/**
 * 
 */
package com.nokia.first.singleton;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author yanachen
 * @create 2020-02-06 11:12:00
 * ����ʽ--�̰߳�ȫ�����ö��̣߳�˫�˼�������
 * 1.˽�й�����
 * 2.˽�еľ�̬����
 * 3.���Ⱪ¶һ�����еľ�̬����
 */
public class FullSingleton2 {
	private static volatile FullSingleton2 instance=null;
	public static FullSingleton2 getInstance() {
		
		if (instance==null) {
			synchronized (FullSingleton2.class) {
				if (instance == null) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					instance = new FullSingleton2();
				}
			} 
		}
		return instance;
		
	}
	private FullSingleton2() {
		
	}

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService es=Executors.newFixedThreadPool(2);
		Future<FullSingleton2>  f1=es.submit(new Callable<FullSingleton2>() {

			@Override
			public FullSingleton2 call() throws Exception {				
				return FullSingleton2.getInstance();
			}
			
		});
		Future<FullSingleton2>  f2=es.submit(new Callable<FullSingleton2>() {

			@Override
			public FullSingleton2 call() throws Exception {				
				return FullSingleton2.getInstance();
			}
			
		});
		
		System.out.println(f1.get());
		System.out.println(f2.get());
		System.out.println(f1.get() == f2.get());
		es.shutdown();
	}

}
