/**
 * 
 */
package com.nokia.first;

import java.util.Arrays;

/**
 * @author yanachen
 * @create 2020-02-06 16:58:32
 * 参数的方法传递机制
 * (1)形参是基本数据类型  ---传递数据值 （值拷贝）
 * (2)实参是引用数据类型  ---传递地址
 *   。传递地址值
 *   。特殊的类型：String,包装类等对象不可变性  --改变时会生成新的对象
 * 
 */
class MyData{
	int a=10;
}
public class MethodParam_04 {
	

	/**
	 * @param args
	 * 局部变量以方法为分隔线
	 * 本题有两个方法：main()方法和change()方法
	 */
	public static void main(String[] args) {
		int i=1; //基本数据类型，放在栈里，传值
		String str="hello";//常量池
		Integer num=2;//堆
		int[] arr= {1,2,3,4,5};//堆
		MyData my=new MyData();//堆
		change(i,str,num,arr,my);
		System.out.println("i="+i);
		System.out.println("str="+str);
		System.out.println("num="+num);
		System.out.println("arr="+Arrays.toString(arr));
		System.out.println("my.a="+my.a);
		
		
	}

	private static void change(int j, String s, Integer n, int[] a, MyData m) {
		j+=1;
		s+="world";
		n+=1;
		a[0]+=1;
		m.a+=1;
	}

}
