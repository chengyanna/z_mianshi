/**
 * 
 */
package com.nokia.first.singleton;

/**
 * @author yanachen
 * @create 2020-02-06 10:36:54
 * ����ʽ��ʽ�������ò��ã�������ʱ��ֱ�Ӵ���������
 * (1)˽�й���������ֹ�ⲿ����
 * ��2�����о�̬ʾ�����󣬼�final���α�����Ψһ�ģ��������޸�
 */
public class HungrySingleton1 {
	public static final HungrySingleton1 instance=new HungrySingleton1();
	private HungrySingleton1() {
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HungrySingleton1 s1=HungrySingleton1.instance;
		HungrySingleton1 s2=HungrySingleton1.instance;
		System.out.println(s1==s2);
		System.out.println(s1);
		System.out.println(s2);
		
	}

}
