/**
 * 
 */
package com.nokia.alibaba;

/**
 * @author yanachen
 * @create 2020-02-04 16:32:36
 */
public class Test {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE); //21亿
		int i=499999999 * 499999999;
		System.out.println(i);
		long s = 499999999 * 499999999;
		System.out.println(s);//由于Java中右侧值的计算默认是int类型
		Runtime rt=Runtime.getRuntime();
		Integer.valueOf(5);
//		test1();
		

	}

	private  static void test1() {
		
//		new Class().newInstance();
		
	}

}
