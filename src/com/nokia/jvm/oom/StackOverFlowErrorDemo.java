/**
 * 
 */
package com.nokia.jvm.oom;

/**
 * @author yanachen
 * @create 2020-01-31 17:39:37
 * 栈管运行，堆管存储
 * 栈的大小默认是512k~1024k,具体大小依赖于所在的系统
 * 在方法递归（深度方法）调用时，方法特别多，容易出现StackOverFlowError
 */
public class StackOverFlowErrorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		createStackOverFlowError();

	}

	private static void createStackOverFlowError() {
		createStackOverFlowError();		//Exception in thread main: java.lang.StackOverflowError
	}

}
