/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 20:47:01
 * 成员变量与局部变量
 * 
 * 考点：
 * 1）就近原则
 * 2）变量的分类
 *   成员变量：类变量，实例变量
 *   局部变量 在方法体，形参，代码块中
 * 3）非静态代码的执行：每次创建实例对象都会执行
 * 4）方法的调用规则：调用一次执行一次
 * 
 * 局部变量 ： 栈
 * 实例变量：堆
 * 类变量： 方法区
 */
public class MemeberVarLocalVarDemo_06 {
	static int s;//成员变量，类变量，在方法区中
	int i;//成员变量，实例变量，在堆中
	int j;//成员变量，实例变量，在堆中
	{
		int i=1;//非静态代码块中的局部变量
		i++;//就近原则，采用局部变量
		j++;
		s++;
	}
	public void test(int j) {
		j++;
		i++;
		s++;		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MemeberVarLocalVarDemo_06 obj1=new MemeberVarLocalVarDemo_06();
		MemeberVarLocalVarDemo_06 obj2=new MemeberVarLocalVarDemo_06();
		obj1.test(10);//i=1;j=1;s=2;
		obj1.test(20);//i=2;j=1;s=3;
		obj2.test(30);//i=1;j=1;s=5
		System.out.println(obj1.i+","+obj1.j+","+obj1.s);
		System.out.println(obj2.i+","+obj2.j+","+obj2.s);

	}
//	2,1,5
//	1,1,5

}
