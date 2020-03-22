/**
 * 
 */
package com.nokia.jvm;

/**
 * @author yanachen
 * ��һ�֣��鿴�����̵�ҵ�
 * jps �鿴java����
 * jinfo �鿴�����java������Ϣ���磺jinfo -flags pid
 *   Boolean����  jinfo -flag PrintGCDetails pid
 *             jinfo -flag UseSerialGC pid
 *   KV��ֵ����    jinfo -flag MetaspaceSize   -XX:MetaspaceSize=21807104
 *   
 *   ����������� -Xms  �ȼ��� -XX:InitialHeapSize  Ĭ�������ڴ��1/64
 *            -Xmx �ȼ���  -XX:MaxHeapSize      Ĭ�������ڴ��1/4
 *            �����޸ĳ�һ�µ�
 *            
 *            -Xss �ȼ���-XX:ThreadStackSize ���õ����߳�ջ�Ĵ�С��һ��Ĭ��Ϊ512k~1024k�� defalut value depends on the platform
 *            ջ�����У��ѹܴ洢
 *            -Xmn ������������С
 *            -XX:MetaspaceSize Ĭ����20M�� �������Ƶ��new���󣬻�ű� ��Ĭ�������ֻ�ܱ����ڴ����ƣ�
 *            -XX:SurvivorRatio=8 ������������eden��S0��S1�ռ�ı�����Ĭ����8:1:1
 *            -XX:NewRation �����������������ڶѽṹ��ռ�ȣ�Ĭ����2����������ռ1�� �����ռ2
 *            -XX:MaxTenuringThrehold ��������������� java8Ĭ��15
 *   �������ð�����-Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC         
 *  �ڶ��֣��鿴�����̵�ҵ�
 *  java -XX:+PrintFlagsInitial      ��������
 *  java -XX:+PrintFlagsFinal -version     JVM���غ��ֵ = := (���޸ĺ��ֵ)
 *  java -XX:+PrintCommandLineFlags
 */
public class JVMXXDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long totalMemory=Runtime.getRuntime().totalMemory();
		long maxMemory=Runtime.getRuntime().maxMemory();
		System.out.println("Total Memory (-Xms) ="+totalMemory+ "(�ֽڡ�)"+(totalMemory/(double)1024/1024)+ "MB");
		System.out.println("Max Memory (-Xmx) ="+maxMemory+ "(�ֽڡ�)"+(maxMemory/(double)1024/1024)+ "MB");
		System.out.println("****Hello");
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
