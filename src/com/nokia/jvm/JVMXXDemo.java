/**
 * 
 */
package com.nokia.jvm;

/**
 * @author yanachen
 * 第一种，查看参数盘点家底
 * jps 查看java进程
 * jinfo 查看具体的java进程信息，如：jinfo -flags pid
 *   Boolean类型  jinfo -flag PrintGCDetails pid
 *             jinfo -flag UseSerialGC pid
 *   KV设值类型    jinfo -flag MetaspaceSize   -XX:MetaspaceSize=21807104
 *   
 *   两个经典参数 -Xms  等价于 -XX:InitialHeapSize  默认物理内存的1/64
 *            -Xmx 等价于  -XX:MaxHeapSize      默认物理内存的1/4
 *            建议修改成一致的
 *            
 *            -Xss 等价于-XX:ThreadStackSize 设置单个线程栈的大小，一般默认为512k~1024k， defalut value depends on the platform
 *            栈管运行，堆管存储
 *            -Xmn 设置新生代大小
 *            -XX:MetaspaceSize 默认是20M， 如果大量频繁new对象，会撑爆 （默认情况下只受本地内存限制）
 *            -XX:SurvivorRatio=8 设置新生代中eden和S0，S1空间的比例，默认是8:1:1
 *            -XX:NewRation 配置年轻代和老年代在堆结构的占比，默认是2，即新生代占1， 老年代占2
 *            -XX:MaxTenuringThrehold 设置垃圾最大年龄 java8默认15
 *   经典设置案例：-Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC         
 *  第二种，查看参数盘点家底
 *  java -XX:+PrintFlagsInitial      出厂设置
 *  java -XX:+PrintFlagsFinal -version     JVM加载后的值 = := (被修改后的值)
 *  java -XX:+PrintCommandLineFlags
 */
public class JVMXXDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long totalMemory=Runtime.getRuntime().totalMemory();
		long maxMemory=Runtime.getRuntime().maxMemory();
		System.out.println("Total Memory (-Xms) ="+totalMemory+ "(字节、)"+(totalMemory/(double)1024/1024)+ "MB");
		System.out.println("Max Memory (-Xmx) ="+maxMemory+ "(字节、)"+(maxMemory/(double)1024/1024)+ "MB");
		System.out.println("****Hello");
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
