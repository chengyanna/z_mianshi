/**
 * 
 */
package com.nokia.jvm.gc;

/**
 * @author yanachen
 * 在java中，可作为GC roots的对象有：
 * 1.虚拟机栈（栈帧中的本地变量表）中引用的对象
 * 2.方法区中的类静态属性引用的对象
 * 3.方法区中常量引用的对象
 * 4.本地方法栈中JNI（即一般说的native方法）中引用的对象
 *
 */
public class GCRootDemo {
	private byte[] byteArray=new byte[100*1024];
//	private static GCRootDemo2 t2;
//	private static final GCRootDemo3 t3=new GCRootDemo3();
	
	public static void m1() {
		GCRootDemo t1=new GCRootDemo();
		System.gc();
		System.out.println("第一次GC完成");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		m1();

	}

}
