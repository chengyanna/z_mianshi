/**
 * 
 */
package com.nokia.jvm.oom;

import java.util.Random;

/**
 * @author yanachen
 * @create 2020-01-31 17:56:32
 * �������ӣ�newһ�������
 * new�Ķ���̫�࣬�ű����ڴ�
 */
public class HeapSpaceDemo {

	/**
	 * @param args
	 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	 * -Xms10m -Xmx10m
	 */
	public static void main(String[] args) {
		String str="nokia";
		while(true) {
			str+=str+ new Random().nextInt(111111)+new Random().nextInt(222222);
			str.intern();
		}
		
		//byte[] byteArray=new byte[30*1024*1024];
		
	}

}
