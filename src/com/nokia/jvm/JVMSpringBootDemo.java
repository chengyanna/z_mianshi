/**
 * 
 */
package com.nokia.jvm;

/**
 * @author yanachen
 * @create 2020-02-01 19:56:06
 * ʵ�ʹ����У���ν��SpringBoot���е��ţ������أ�
 * JVMGC�������� ����springboot΢�������������͵����Ż�
 * 
 * 1.����΢���񹤳�
 * 2.mvn clean package 
 * 3.΢����������ʱ��ͬʱ����JVM/GC���Ų���
 * 4.��ʽ java -server jvm�ĸ��ֲ���  -jar ��һ�����ɵ�jar��/war�� (��serverģʽ����java vm)
 * �� java -server -Xms1024m -Xmx1024 -XX:+UseG1GC hello.war
 */
public class JVMSpringBootDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
