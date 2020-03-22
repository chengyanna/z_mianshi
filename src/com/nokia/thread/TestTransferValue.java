/**
 * 
 */
package com.nokia.thread;

class Person{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}	
	public Person(String name) {		
		this.name = name;
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Person() {
		super();
	}	
	
}

/**
 * @author yanachen
 *
 */
public class TestTransferValue {
	public void changeValue1(int age) {
		age=30;		
	}
	
	public void changeValue2(Person person) {
		person.setName("xxx");			
	}
	
	public void changeValue3(String str) {
		str="xxx";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//��24_transftervalue����С��ϰ
		TestTransferValue test=new TestTransferValue();
		int age=20;
		test.changeValue1(30);
		System.out.println("age----"+age);//20 �����������ͣ�ֵ����
		
		Person person=new Person("abc",11);
		test.changeValue2(person);
		System.out.println("name----"+person.getName());///xxx ָ������
		
		String str="abc"; //Thread.currentThread().getName()   No template propals
		test.changeValue3(str);
		System.out.println("str----"+str);//abc String�Ƚ����⣬���ַ�����������ȼ���ڳ���������û�У�û�еĻ����½�һ��
		//main ָ��abc, ������Ҳָ��abc,�����޸�֮����Ϊ��������û��xxx,�����½�xxx,������ָ��xxx, ��Ŀ��Ҫ��ӡmainָ��˭��������abc

	}

}
