/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 13:32:21
 * �����ʼ����
 * ��1��<clinit>()�����еľ�̬������ʾ��ֵ����public static int method()
 * (2)<clinit>()�����еľ�̬����� * 
 * 
 * �ȳ�ʼ�����࣬
 * �ٳ�ʼ������
 * 
 * ����ʵ����������
 * ��1������super();��ǰ ��9����3����2��
 * ��2������init() �Ǿ�̬ʵ��������ʾ��ֵ test() ��9��
 * ��3������init() �Ǿ�̬����� ��8��
 * ��4������init() �޲ι��� Son() ��7��
 * 
 * ��Ϊ����������Son�������ʵ��������<init>ִ������(9)(3)(2)(9)(8)(7)
 */
public class Son_03 extends Father_03{
	
	private int i=test();
	private static int j=method();
	static {
		System.out.print("(6)");
	}
	Son_03(){
//		super();//д��д���ڣ������๹������һ������ø���Ĺ���������<init>����
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
