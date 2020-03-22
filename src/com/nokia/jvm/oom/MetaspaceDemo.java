/**
 * 
 */
package com.nokia.jvm.oom;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author yanachen
 * @create 2020-01-31 21:40:39
 * 
 * Caused by: java.lang.OutOfMemoryError: Metaspace
 * JVM参数：-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m 
 * 
 * Java8及之后的版本使用metaspace代替永久代
 * 
 * Metaspace是方法区在HotSpot中的实现，他与永久代最大的区别在于：metaspace并不在虚拟机内存中而是使用本地内存
 * 也即在java8中，classes metadata(the virtual machines internal presentation of Java class),
 * 被存储在叫做metaspace的native memory
 * 
 * 永久代（java8中被metaspace取代了）存放了以下信息：
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 * 
 * 模拟Metaspace空间溢出，我们不断生成类往空间里灌，类占据的空间总会超过MetaspaceSize的
 * 通过java -XX:+PrintFlagsInitial查看本机初始化参数， 系统默认 MetaspaceSize大概是21M
 */
public class MetaspaceDemo {
	static class OOMTest{
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i=0;//模拟计算多少次后发生异常
		
		try {
			while(true) {
				i++;
				Enhancer enhancer=new Enhancer(); //spring cglib动态字节码技术
				enhancer.setSuperclass(OOMTest.class);
				enhancer.setUseCache(false);
				enhancer.setCallback(new net.sf.cglib.proxy.MethodInterceptor() {

					@Override
					public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
						return arg3.invokeSuper(arg0, args);
					}
					
				});
				
				enhancer.create();
				
			}
		} catch (Throwable e) {
			System.out.println("******i进行"+i+"次后出现metaspace error");
			e.printStackTrace();
		}

	}

}
