/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 13:32:21
 * 类初始化
 * 1）一个类要创建实例需要先加载并初始化该类
 *    main方法所在的类需要先加载和初始化
 * 2）一个子类要初始化需要先初始化父类
 * 3）一个类初始化就是执行<clinit>()方法 （编译器自动生成的）
 *   <clinit>()方法由静态类变量显示赋值代码和静态代码块组成
 *   类变量显示赋值代码和静态代码块代码从上到下顺序执行
 *   <clinit>()方法只执行一次
 * 实例初始化
 * 1）实例初始化就是执行<init>()方法
 * 	*<init>()方法可能重载有多个，有几个构造器就有几个<init>方法
 *  *<init>()方法由非静态实例变量显示赋值代码和非静态代码块、对应构造器代码组成
 *  *非静态实例变量显示赋值代码和非静态代码块代码从上到下顺序执行，构造器的代码最后执行
 *  *每次创建实例对象，调用对应的构造器，执行的就是对应的<init>()方法
 *  *<init>方法的首行是super()或super(实参列表)，即对应父类的<init>方法
 * 
 * 方法的重写
 * 1)哪些方法不可以被重写
 *    。final方法
 *    。静态方法
 *    。private等子类中不可见方法
 * 2）对象的多态性
 *   。子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的代码
 *   。非静态方法默认的调用对象是this
 *   。this在构造器或者说<init>方法中就是正在创建的对象
 * 
 * 父类初始化：
 * （1）<clinit>()方法中的静态变量显示赋值代码public static int method()
 * (2)<clinit>()方法中的静态代码块
 * 
 * 父类实例化方法：
 * （1）父类super();最前
 * （2）父类init() 非静态实例对象显示赋值this.test() 
 * （3）父类init() 非静态代码块
 * （4）父类init() 无参构造 Father()
 * 
 * 非静态方法前面其实有一个默认的对象this
 * this在构造器（或<init>）他表示的是正在创建的对象，因为这里是在创建Son对象，所以test()执行的是子类重写的代码 （面向对象多态）
 * 
 */
public class Father_03 {
	
	private int i=test();
	private static int j=method();
	static {
		System.out.print("(1)");
	}
	Father_03(){
		System.out.print("(2)");
	}
	{
		System.out.print("(3)");
	}

	public int test() {
		System.out.print("(4)");
		return 1;
	}

	public static int method() {
		System.out.print("(5)");
		return 1;
	}
	
	public static void main(String[] args) {
		Father_03 f=new Father_03();//(5)(1)(4)(3)(2)
	}

}
