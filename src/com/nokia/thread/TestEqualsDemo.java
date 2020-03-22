/**
 * 
 */
package com.nokia.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yanachen
 * @create 2020-02-10 10:35:36
 * ==既可以比较基本数据类型，还可以比较引用，引用比较的是地址
 * new出来的东西==永false
 * 
 * equals比较的东西不确定，看有没有重写过，Object的equals 默认是==
 * public boolean equals(Object obj) {
        return (this == obj);
    }
    
 * equals要和hashcode要同时重写
 * 
 * HashSet底层实现是HashMap, key是Element，value是Object常量，无序无重复， HashMap比较是否重复，判断的是hashcode，例如重名，用身份证号比较
 */
public class TestEqualsDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1=new String("abc");
		String s2=new String("abc");
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
		Set<String> set01=new HashSet<>();//底层实现是HashMap, key是Element，value是Object常量，无序无重复， HashMap比较是否重复，判断的是hashcode，例如重名，用身份证号比较
		set01.add(s1);
		set01.add(s2);
		System.out.println(s1.hashCode()+"\t"+s2.hashCode());
		System.out.println(set01.size());
		
		System.out.println("============================");
		Person p1=new Person("abc");
		Person p2=new Person("abc");
		System.out.println(p1==p2);
		System.out.println(p1.equals(p2));
		Set<Person> set02=new HashSet<>();
		set02.add(p1);
		set02.add(p2);
		System.out.println(p1.hashCode()+"\t"+p2.hashCode());
		System.out.println(set02.size());

	}

}
