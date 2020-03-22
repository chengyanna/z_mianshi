/**
 * 
 */
package com.nokia.jvm.oom;

import java.util.concurrent.TimeUnit;

/**
 * @author yanachen
 * @create 2020-01-31 21:04:45
 * ��Ƶ����
 * �߲������������ʱ�������������´���java.lang.OutOfMemoryError:unable to create new native thread
 * ׼ȷ�ý���native thread�쳣���Ӧ��ƽ̨�йأ�linux ��ͨ�û�Ĭ��1024���̣߳�root�û�û���ƣ�windows����û����
 * 
 * ����ԭ��
 * 1.Ӧ�ô������߳�̫���ˣ�һ�����̴�������̣߳�����ϵͳ���ؼ���
 * 2.��ķ����������������Ӧ�ó��򴴽���ô���̣߳�linuxϵͳĬ�����������̿��Դ������߳�����1024��������������ᱨjava.lang.OutOfMemoryError:unable to create new native thread
 * 
 * ����취��
 * 1.��취����Ӧ�ó��򴴽��̵߳�����������Ӧ���Ƿ������Ҫ������ô���̣߳�������ǣ��Ĵ��뽫�߳����������
 * 2.�����е�Ӧ�ã�ȷʵ��Ҫ�����ܶ��̣߳�Զ����linuxϵͳĬ�ϵ�1024���̵߳����ƣ�����ͨ���޸�linux���������ã�����linuxĬ������
 * 
 * ��ʾ����root�û���¼linuxϵͳ����
 * ����������������ţ�ulimit -u 
 * vim /etc/security/limits.d/90-nproc.conf
 * ���ֳ�root�û�������Ĭ����1024�������޸�ָ��ĳһ�û����߳���
 */
public class UnableCreateNativeThreadDemo {

	/**
	 * @param args
	 * Thread.start�ڲ�����start0�������÷�����native�ģ�private native void start0();
	 * ������unable to create new native thread
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
