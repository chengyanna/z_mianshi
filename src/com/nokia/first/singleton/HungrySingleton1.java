/**
 * 
 */
package com.nokia.first.singleton;

/**
 * @author yanachen
 * @create 2020-02-06 10:36:54
 * 饿汉式方式不管你用不用，创建类时就直接创建出来了
 * (1)私有构造器，禁止外部创建
 * （2）共有静态示例对象，加final修饰表明是唯一的，不允许修改
 */
public class HungrySingleton1 {
	public static final HungrySingleton1 instance=new HungrySingleton1();
	private HungrySingleton1() {
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HungrySingleton1 s1=HungrySingleton1.instance;
		HungrySingleton1 s2=HungrySingleton1.instance;
		System.out.println(s1==s2);
		System.out.println(s1);
		System.out.println(s2);
		
	}

}
