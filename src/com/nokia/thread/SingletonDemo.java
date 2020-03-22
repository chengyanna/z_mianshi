package com.nokia.thread;

/**
 * 单例模式三步骤 1，new一个static变量赋值为null 2.空的构造方法 3.getInstance
 *
 */
public class SingletonDemo {
	private static volatile SingletonDemo instance=null;
	private SingletonDemo() {
		System.out.println("SingletonDemo was created!!!");
	}
	
//	public static SingletonDemo getInstance() {
//		if(instance==null)
//			instance=new SingletonDemo();
//		return instance;
//	}
	//DCL (Double Check Lock双端检锁机制) new SingletonDemo()
	//memory=allocate();//1.分配内存空间
	//instance(memory);//2.初始化对象
	//instance=memory;//3.设置instance的指向刚分配的内存地址，此时instance!=null
	//指令重排可能出现1,3,2, instance!=null但是还没完成初始化，导致return一个未初始化的对象，线程不安全，所以要加上volatile
	public static SingletonDemo getInstance() {
		if(instance==null)
		{
			synchronized(SingletonDemo.class) {
				if(instance==null)					
					instance=new SingletonDemo();
			}
		}
		return instance;
	}
	public static void main(String[] args) {
		//单线程下的操作
//		SingletonDemo was created!!!被打印了一次
//		true
//		true
//		true
//		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//		
//		//多线程下的操作
		//启动20个线程 SingletonDemo was created!!!被打印了N多次，不确定
		//解决方法：1.在getInstance方法加上synchronized,太重了，数据一致了，但是性能降低了 2.DCL+volatile
		for(int i=1;i<=20;i++) {
			new Thread(()->{
				SingletonDemo.getInstance();				
			},String.valueOf(i)).start();
			
		}

	}

}
