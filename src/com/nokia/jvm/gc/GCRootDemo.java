/**
 * 
 */
package com.nokia.jvm.gc;

/**
 * @author yanachen
 * ��java�У�����ΪGC roots�Ķ����У�
 * 1.�����ջ��ջ֡�еı��ر����������õĶ���
 * 2.�������е��ྲ̬�������õĶ���
 * 3.�������г������õĶ���
 * 4.���ط���ջ��JNI����һ��˵��native�����������õĶ���
 *
 */
public class GCRootDemo {
	private byte[] byteArray=new byte[100*1024];
//	private static GCRootDemo2 t2;
//	private static final GCRootDemo3 t3=new GCRootDemo3();
	
	public static void m1() {
		GCRootDemo t1=new GCRootDemo();
		System.gc();
		System.out.println("��һ��GC���");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		m1();

	}

}
