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
		//见24_transftervalue醒脑小练习
		TestTransferValue test=new TestTransferValue();
		int age=20;
		test.changeValue1(30);
		System.out.println("age----"+age);//20 基本数据类型，值拷贝
		
		Person person=new Person("abc",11);
		test.changeValue2(person);
		System.out.println("name----"+person.getName());///xxx 指针引用
		
		String str="abc"; //Thread.currentThread().getName()   No template propals
		test.changeValue3(str);
		System.out.println("str----"+str);//abc String比较特殊，在字符串常量池里，先检查在常量池里有没有，没有的话，新建一个
		//main 指向abc, 函数内也指向abc,但是修改之后，因为常量池里没有xxx,所以新建xxx,函数内指向xxx, 题目中要打印main指向谁，所以是abc

	}

}
