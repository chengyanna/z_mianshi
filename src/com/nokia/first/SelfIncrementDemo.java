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
	 * 01_��������
	 * �ֲ�������   ������ջ
	 * i=i++ִ�й���
	 * 1.��=���ұߵ�ֵ����ѹ��ջ�У�����i��ֵѹ�������ջ 1
	 * 2.i��������1�� i������Ϊ2
	 * 3.�Ѳ�����ջ�е�ֵ����i,�ֲ��������е�i�ֱ��1��
	 * 
	 * �Ա�class�ֽ����ļ���������
	 * ����     �ֲ�������   ������ջ
	 * i      1-�� 2 ->>3->>4    1
	 * j       1      1
	 * k             2
	 *               3 
	 *               3
	 *               
	 * 	2+3*3=11			
	 */
	public static void main(String[] args) {
		int i=1;
		i=i++; //iload ѹ��ջ  istore��ֵ
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
