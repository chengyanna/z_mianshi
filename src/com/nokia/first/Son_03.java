/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 13:32:21
 * 子类初始化：
 * （1）<clinit>()方法中的静态变量显示赋值代码public static int method()
 * (2)<clinit>()方法中的静态代码块 * 
 * 
 * 先初始化父类，
 * 再初始化子类
 * 
 * 子类实例化方法：
 * （1）父类super();最前 （9）（3）（2）
 * （2）子类init() 非静态实例对象显示赋值 test() （9）
 * （3）子类init() 非静态代码块 （8）
 * （4）子类init() 无参构造 Son() （7）
 * 
 * 因为创建了两个Son对象，因此实例化方法<init>执行两次(9)(3)(2)(9)(8)(7)
 */
public class Son_03 extends Father_03{
	
	private int i=test();
	private static int j=method();
	static {
		System.out.print("(6)");
	}
	Son_03(){
//		super();//写或不写都在，在子类构造器中一定会调用父类的构造器，即<init>方法
		System.out.print("(7)");
	}
	{
		System.out.print("(8)");
	}

	public int test() {
		System.out.print("(9)");
		return 1;
	}

	public static int method() {
		System.out.print("(10)");
		return 1;
	}
	
	public static void main(String[] args) {
		Son_03 s1=new Son_03();
		System.out.println();
		Son_03 s2=new Son_03();
		
//		(5)(1)(10)(6)(9)(3)(2)(9)(8)(7)
//		(9)(3)(2)(9)(8)(7)
	}

}
