/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yanachen
 * AtomicReference 原子引用
 *
 */
class User{
	private String username;
	private int age;
	
	
	public User() {
		super();
	}
	public User(String username, int age) {
		super();
		this.username = username;
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + "]";
	}
	
	
}
public class AtomicReferenceDemo {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		User user1=new User("zhangsan",25);
		User user2=new User("lisi",22);
		AtomicReference<User> atomicReference=new AtomicReference<User>();
		atomicReference.set(user1);
		System.out.println(atomicReference.compareAndSet(user1, user2) +"\t"+atomicReference.get().toString());
		System.out.println(atomicReference.compareAndSet(user1, user2) +"\t"+atomicReference.get().toString());
//		true	User [username=lisi, age=22]
//		false	User [username=lisi, age=22]

	}

}
