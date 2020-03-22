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
 * 饱汉式--线程安全（适用多线程）静态内部类,在内部类被加载和初始化时，创建instance对象
 * 静态内部类不会随着外部类的加载和初始化而初始化，它是要单独加载和初始化的
 * 1.私有构造器
 * 2.私有的静态对象 
 * 3.静态内部类
 * 4.对外暴露一个共有的静态方法
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
