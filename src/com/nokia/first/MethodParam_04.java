/**
 * 
 */
package com.nokia.first;

import java.util.Arrays;

/**
 * @author yanachen
 * @create 2020-02-06 16:58:32
 * �����ķ������ݻ���
 * (1)�β��ǻ�����������  ---��������ֵ ��ֵ������
 * (2)ʵ����������������  ---���ݵ�ַ
 *   �����ݵ�ֵַ
 *   ����������ͣ�String,��װ��ȶ��󲻿ɱ���  --�ı�ʱ�������µĶ���
 * 
 */
class MyData{
	int a=10;
}
public class MethodParam_04 {
	

	/**
	 * @param args
	 * �ֲ������Է���Ϊ�ָ���
	 * ����������������main()������change()����
	 */
	public static void main(String[] args) {
		int i=1; //�����������ͣ�����ջ���ֵ
		String str="hello";//������
		Integer num=2;//��
		int[] arr= {1,2,3,4,5};//��
		MyData my=new MyData();//��
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
