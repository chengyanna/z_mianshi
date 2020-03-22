/**
 * 
 */
package com.nokia.first.singleton;

/**
 * @author yanachen
 * @create 2020-02-06 11:12:00
 * 饱汉式--线程不安全（适用单线程）
 * 1.私有构造器
 * 2.私有的静态对象
 * 3.对外暴露一个共有的静态方法
 */
public class FullSingleton1 {
	private static FullSingleton1 instance=null;
	public static FullSingleton1 getInstance() {
		if(instance==null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance=new FullSingleton1();
		}
		return instance;
		
	}
	private FullSingleton1() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FullSingleton1 s1=FullSingleton1.getInstance();
		FullSingleton1 s2=FullSingleton1.getInstance();
		System.out.println(s1==s2);
		System.out.println(s1);
		System.out.println(s2);
	}

}
