/**
 * 
 */
package com.nokia.jvm.oom;

/**
 * @author yanachen
 * @create 2020-01-31 17:39:37
 * ջ�����У��ѹܴ洢
 * ջ�Ĵ�СĬ����512k~1024k,�����С���������ڵ�ϵͳ
 * �ڷ����ݹ飨��ȷ���������ʱ�������ر�࣬���׳���StackOverFlowError
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
