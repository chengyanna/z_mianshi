/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 08:03:04
 */
public class SelfIncrementDemo {

	/**
	 * @param args
	 * 01_自增变量
	 * 局部变量表   操作数栈
	 * i=i++执行过程
	 * 1.把=号右边的值依次压入栈中，即把i的值压入操作数栈 1
	 * 2.i变量自增1， i变量变为2
	 * 3.把操作数栈中的值赋给i,局部变量表中的i又变成1了
	 * 
	 * 对比class字节码文件来看过程
	 * 变量     局部变量表   操作数栈
	 * i      1-》 2 ->>3->>4    1
	 * j       1      1
	 * k             2
	 *               3 
	 *               3
	 *               
	 * 	2+3*3=11			
	 */
	public static void main(String[] args) {
		int i=1;
		i=i++; //iload 压入栈  istore赋值
//		System.out.println(i);
		int j=i++;
//		System.out.println(j);
//		System.out.println(i);
		int k=i+ ++i *i++;
		System.out.println("i="+i);
		System.out.println("j="+j);
		System.out.println("k="+k);
//		i=4
//		j=1
//		k=11

	}

}
