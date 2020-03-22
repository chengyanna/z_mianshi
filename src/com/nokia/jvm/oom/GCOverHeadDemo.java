/**
 * 
 */
package com.nokia.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanachen
 * @create 2020-01-31 18:17:21
 * JVM参数演示配置
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 
 * GC回收时间过长时会抛出Out of Memory error,过长的定义是超过98%的时间用来做GC并且回收了不到2%的堆内存  GC过头
 * 连续多次GC都只回收了不到2%的极端情况下才会抛出，假如不抛出GC Overhead limit exceeded 会发生什么呢
 * 那就是清理的堆内存很快被再次填满，迫使GC再次执行，这样就形成恶性循环，CPU使用率一直是100%，而GC却没有任何效果
 */
public class GCOverHeadDemo {

	/**
	 * @param args
	 * java.lang.OutOfMemoryError: GC overhead limit exceeded
	 */
	public static void main(String[] args) {
		int i=0;
		List<String> list=new ArrayList<String>();
		try {
			while(true) {			
//				list.add(String.valueOf(i).intern()); //如果是i,报Java heap space, at java.util.ArrayList.add(ArrayList.java:462)
				list.add(String.valueOf(++i).intern());//如果是++i,String.valueOf 底层是 java.lang.Integer.toString(Integer.java:401),报java.lang.OutOfMemoryError: GC overhead limit exceeded
			}
		} catch (Throwable e) {
			System.out.println("********i:"+i);
			throw e;
		}

	}

}
