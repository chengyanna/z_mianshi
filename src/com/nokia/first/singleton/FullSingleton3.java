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
 * @create 2020-02-06 11:28:32
 * ����ʽ--�̰߳�ȫ�����ö��̣߳���̬�ڲ���,���ڲ��౻���غͳ�ʼ��ʱ������instance����
 * ��̬�ڲ��಻�������ⲿ��ļ��غͳ�ʼ������ʼ��������Ҫ�������غͳ�ʼ����
 * 1.˽�й�����
 * 2.˽�еľ�̬���� 
 * 3.��̬�ڲ���
 * 4.���Ⱪ¶һ�����еľ�̬����
 *
 */
public class FullSingleton3 {
	
	
	private FullSingleton3() {
		
	}
	private static class inner{
		private static final FullSingleton3 instance=new FullSingleton3();
	}
	public static FullSingleton3 getInstance() {
		return inner.instance;
	}

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {		
		ExecutorService es=Executors.newFixedThreadPool(2);
		Future<FullSingleton3>  f1=es.submit(new Callable<FullSingleton3>() {

			@Override
			public FullSingleton3 call() throws Exception {				
				return FullSingleton3.getInstance();
			}
			
		});
		Future<FullSingleton3>  f2=es.submit(new Callable<FullSingleton3>() {

			@Override
			public FullSingleton3 call() throws Exception {				
				return FullSingleton3.getInstance();
			}
			
		});
		
		System.out.println(f1.get());
		System.out.println(f2.get());
		System.out.println(f1.get() == f2.get());
		es.shutdown();

	}

}
