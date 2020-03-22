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
public class TestEqualsDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1="abc";//在常量池里新生成一个
		String s2=new String("abc");//放在堆里
		String s3="abc";//从常量池里取
		String s4="xxx";
		String s5="abc"+"xxx";
		String s6=s3+s4;//+会生成新的对象
		String s7=s3+s4;//+会生成新的对象
		
		System.out.println(s1==s2);//false
		System.out.println(s1==s5);//false
		System.out.println(s1==s6);//false
		System.out.println(s1==s6.intern());//false
		System.out.println(s2==s2.intern());//false调用intern()方法时，如果常量池里存在和该字符串equals的值，则直接从池子里取出来；如果不存在，则往池子里加入该string,然后返回对象的引用
		                                    //s2在堆里，s2.intern()在常量池，所以地址不相等，== false
		System.out.println(s1==s2.intern());//true s1,s2.intern()都在常量池里，且值相等，返回true
		System.out.println(s5==s6);//false
		System.out.println(s6==s7);//false
		System.out.println(s5==s6.intern());
		/**
	     * Returns a canonical representation for the string object.
	     * <p>
	     * A pool of strings, initially empty, is maintained privately by the
	     * class {@code String}.
	     * <p>
	     * When the intern method is invoked, if the pool already contains a
	     * string equal to this {@code String} object as determined by
	     * the {@link #equals(Object)} method, then the string from the pool is
	     * returned. Otherwise, this {@code String} object is added to the
	     * pool and a reference to this {@code String} object is returned.
	     * */

	}

}
