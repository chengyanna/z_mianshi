package com.nokia.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanachen
 * 何为CAS  比较并交换 CompareAndSet
 *
 */
public class CASDemo {

	public static void main(String[] args) {
		//主物理内存变量的值为5
		AtomicInteger atomicInteger=new AtomicInteger(5);
		
		//main线程修改工作内存中变量的值为2019，并试图写回主物理内存，写回之前先比较，他期望的值是5，即主物理内存中的变量没有被修改过
		System.out.println(atomicInteger.compareAndSet(5, 2019)+" atomicInteger="+atomicInteger.get());
		
		//假设另外一个线程也试图修改变量的值,没有修改成功，因为主物理内存的值改变了
		System.out.println(atomicInteger.compareAndSet(5, 1024)+" atomicInteger="+atomicInteger.get());

		//result:
//		true atomicInteger=2019
//		false atomicInteger=2019
		
		atomicInteger.getAndIncrement();
	}

}
