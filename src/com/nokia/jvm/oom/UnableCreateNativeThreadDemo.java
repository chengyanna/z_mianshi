/**
 * 
 */
package com.nokia.jvm.oom;

import java.util.concurrent.TimeUnit;

/**
 * @author yanachen
 * @create 2020-01-31 21:04:45
 * 高频考点
 * 高并发请求服务器时，经常出现如下错误：java.lang.OutOfMemoryError:unable to create new native thread
 * 准确得讲该native thread异常与对应的平台有关，linux 普通用户默认1024个线程，root用户没限制，windows好像没限制
 * 
 * 导致原因：
 * 1.应用创建的线程太多了，一个进程创建多个线程，超过系统承载极限
 * 2.你的服务器并不允许你的应用程序创建那么多线程，linux系统默认允许单个进程可以创建的线程数是1024个，超过这个，会报java.lang.OutOfMemoryError:unable to create new native thread
 * 
 * 解决办法：
 * 1.想办法降低应用程序创建线程的数量，分析应用是否真的需要创建这么多线程，如果不是，改代码将线程数降到最低
 * 2.对于有的应用，确实需要创建很多线程，远超过linux系统默认的1024个线程的限制，可以通过修改linux服务器配置，扩大linux默认限制
 * 
 * 演示：非root用户登录linux系统测试
 * 服务器级别参数调优：ulimit -u 
 * vim /etc/security/limits.d/90-nproc.conf
 * 发现出root用户外其他默认是1024，可以修改指定某一用户的线程数
 */
public class UnableCreateNativeThreadDemo {

	/**
	 * @param args
	 * Thread.start内部调用start0方法，该方法是native的，private native void start0();
	 * 所以是unable to create new native thread
	 */
	public static void main(String[] args) {
		for (int i = 0; ; i++) {
			System.out.println("********i="+i);
			new Thread(() -> {
				try {					
					TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, i+"").start();
		}

	}

}
