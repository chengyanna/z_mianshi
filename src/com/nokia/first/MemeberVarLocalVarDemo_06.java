/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 20:47:01
 * ��Ա������ֲ�����
 * 
 * ���㣺
 * 1���ͽ�ԭ��
 * 2�������ķ���
 *   ��Ա�������������ʵ������
 *   �ֲ����� �ڷ����壬�βΣ��������
 * 3���Ǿ�̬�����ִ�У�ÿ�δ���ʵ�����󶼻�ִ��
 * 4�������ĵ��ù��򣺵���һ��ִ��һ��
 * 
 * �ֲ����� �� ջ
 * ʵ����������
 * ������� ������
 */
public class MemeberVarLocalVarDemo_06 {
	static int s;//��Ա��������������ڷ�������
	int i;//��Ա������ʵ���������ڶ���
	int j;//��Ա������ʵ���������ڶ���
	{
		int i=1;//�Ǿ�̬������еľֲ�����
		i++;//�ͽ�ԭ�򣬲��þֲ�����
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
