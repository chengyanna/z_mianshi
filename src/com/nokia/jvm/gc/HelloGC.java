/**
 * 
 */
package com.nokia.jvm.gc;

/**
 * @author yanachen
 * @create 2020-02-01 08:23:12
 * ������¼�� ���Ϸɵ������Ȼ����ص�ʵ��
 * 
 * �Ĵ����������㷨��1.���ü����� ��deprecated�� 2.���� ����Ҫ����yough����3.������(Mark-Swipe),�����ڴ���Ƭ  4.���ѹ����Mark-Compat�� ���ǰ������û���ڴ���Ƭ�ˣ��������ظ�����Ҫ������Դ
 * 
 * ���ʵ�� (������������)1. ���л��� SerialGC 2.���л��� ParalellGC 3.CMS (Concurrent Mark Swipe)�������������� 4.G1 ���������գ�
 * 
 * �ߴ�������������1. UseSerialGC 2.UseParalellGC 3.UseParallelOldGC  4.UseConcMarkSweepGC 5.UseParNewGC 6.UseG1GC  7. UseSerialOldGC (deprecated)
 * 
 * �鿴ʹ�õ�Ĭ������������  java -XX:+PrintCommandLineFlags -version
 * 
 * 1. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC (DefNew+Tenured)
 * 2. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC (ParaNew+Tenured)
 * Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 * 3. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC(PSYoungGen+ParOldGen) Parallel Scavenge ���д�ɨ���
 * 4.
 *   4.1 -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC(PSYoungGen+ParOldGen) ���Ժ�UseParallelGC���༤��
 *   4.2 ����Ĭ�Ͼ���UseParalleGC -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags (PSYoungGen+ParOldGen)
 * 5. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC (ParaNew+CMS) 
 * 6. -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 * 7. �������У�����java8���Ժ��Ż��� -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
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
