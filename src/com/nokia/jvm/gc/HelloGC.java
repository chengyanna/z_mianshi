/**
 * 
 */
package com.nokia.jvm.gc;

/**
 * @author yanachen
 * @create 2020-02-01 08:23:12
 * 周阳语录： 天上飞的理念，必然有落地的实现
 * 
 * 四大垃圾回收算法：1.引用计数法 （deprecated） 2.复制 （主要用于yough区）3.标记清除(Mark-Swipe),产生内存碎片  4.标记压缩（Mark-Compat） 清除前滑动，没有内存碎片了，但是来回复制需要消耗资源
 * 
 * 落地实现 (垃圾回收类型)1. 串行回收 SerialGC 2.并行回收 ParalellGC 3.CMS (Concurrent Mark Swipe)并发标记清除回收 4.G1 （分区回收）
 * 
 * 七大垃圾回收器：1. UseSerialGC 2.UseParalellGC 3.UseParallelOldGC  4.UseConcMarkSweepGC 5.UseParNewGC 6.UseG1GC  7. UseSerialOldGC (deprecated)
 * 
 * 查看使用的默认垃圾回收器  java -XX:+PrintCommandLineFlags -version
 * 
 * 1. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC (DefNew+Tenured)
 * 2. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC (ParaNew+Tenured)
 * Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 * 3. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC(PSYoungGen+ParOldGen) Parallel Scavenge 并行打扫清除
 * 4.
 *   4.1 -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC(PSYoungGen+ParOldGen) 可以和UseParallelGC互相激活
 *   4.2 不加默认就是UseParalleGC -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags (PSYoungGen+ParOldGen)
 * 5. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC (ParaNew+CMS) 
 * 6. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 * 7. 理论上有，但是java8及以后优化了 -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 * 
 */
public class HelloGC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] byteArray=new byte[12*1024*1024];

	}

}
