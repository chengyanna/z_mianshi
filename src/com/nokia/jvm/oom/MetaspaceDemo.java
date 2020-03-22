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
 * JVM������-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m 
 * 
 * Java8��֮��İ汾ʹ��metaspace�������ô�
 * 
 * Metaspace�Ƿ�������HotSpot�е�ʵ�֣��������ô������������ڣ�metaspace������������ڴ��ж���ʹ�ñ����ڴ�
 * Ҳ����java8�У�classes metadata(the virtual machines internal presentation of Java class),
 * ���洢�ڽ���metaspace��native memory
 * 
 * ���ô���java8�б�metaspaceȡ���ˣ������������Ϣ��
 * ��������ص�����Ϣ
 * ������
 * ��̬����
 * ��ʱ�����Ĵ���
 * 
 * ģ��Metaspace�ռ���������ǲ������������ռ���࣬��ռ�ݵĿռ��ܻᳬ��MetaspaceSize��
 * ͨ��java -XX:+PrintFlagsInitial�鿴������ʼ�������� ϵͳĬ�� MetaspaceSize�����21M
 */
public class MetaspaceDemo {
	static class OOMTest{
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i=0;//ģ�������ٴκ����쳣
		
		try {
			while(true) {
				i++;
				Enhancer enhancer=new Enhancer(); //spring cglib��̬�ֽ��뼼��
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
			System.out.println("******i����"+i+"�κ����metaspace error");
			e.printStackTrace();
		}

	}

}
