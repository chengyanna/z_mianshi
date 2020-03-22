/**
 * 
 */
package com.nokia.jvm.ref;

import java.lang.ref.SoftReference;

/**
 * @author yanachen
 * 对于只有软引用的对象来说，内存够用的时候就保留，不够用就回收
 * 
 * 软引用和弱引用适应场景： Mybatis缓存
 * 假如一个应用需要需要读取大量的本地图片：
 * 	。如果每次读取图片都从硬盘读取会严重影响性能
 *  。如果一次性全部加载到内存中有可能造成内存溢出
 *  
 *  此时软引用或者弱引用可以解决这个问题
 *  
 *  设计思路：用一个HashMap来保存图片的路径和相应图片对象关联的软引用之间的映射关系，在内存不足时JVM会自动回收这些缓存图片对象占用的空间，从而有效避免OOM问题
 *  Map<String,SoftReference<Bitmap>> map=new HashMap<String,SoftReference<Bitmap>>();
 *
 */
public class SoftReferenceDemo {
	
//	 内存够用的时候就保留，不够用就回收
	public static void softRef_Memory_Enough() {
		Object o1=new Object();
		SoftReference<Object> o2=new SoftReference<Object>(o1);
		System.out.println(o1);
		System.out.println(o2.get());
		System.out.println();
		
		o1=null;
		System.gc();
		System.out.println(o1);
		System.out.println(o2.get());
		System.out.println("===============================");
	}

	/*
	 * JVM配置，故意产生大对象并配置小的内存，让它内存不够用了导致OOM，看软引用的回收情况
	 * -Xms5m -Xmx5m -XX:+PrintGCDetails
	 */
	public static void softRef_Memory_NotEnough() {
		Object o1=new Object();
		SoftReference<Object> o2=new SoftReference<Object>(o1);
		System.out.println(o1);
		System.out.println(o2.get());
		System.out.println();
		o1=null;
		
		try {
			byte[] byteArray=new byte[30*1024*1024];
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(o1);
			System.out.println(o2.get());
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		softRef_Memory_Enough();
		
		softRef_Memory_NotEnough();
	}

}
