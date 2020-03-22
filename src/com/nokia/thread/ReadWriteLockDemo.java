
package com.nokia.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Cache������ 1.put 2.get 3.clear �ײ�ʵ����Map�ӿڼ���ʵ���࣬һ��Ҫ��Volatile�ɼ��ԣ���֤�޸��ܼ�ʱ��֪ͨ��
 */
class MyCache{
	volatile Map<String,Object> map=new HashMap<String,Object>();
	ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
	public void put(String key,Object value) {
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t ����д�룺");
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName()+"\t д�����");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.writeLock().unlock();
		}
	}
	public void get(String key) {
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t ���ڶ�ȡ��");
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"\t ��ȡ��� "+map.get(key));
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.readLock().unlock();
		}
	}
	public void clear() {
		map.clear();
	}
}
/**
 * @author yanachen
 * ����߳�ͬʱ��һ����Դ��û�����⣬Ϊ�����㲢��������ȡ������ԴӦ�ÿ���ͬʱ���С�
 * ������һ���߳���ȥд������Դ���Ͳ�Ӧ�����������߳̿��ԶԸ���Դ���ж�����д
 * С�ܽ᣺
 *     ��-���ܹ���
 *     ��-д���ܹ���
 *     д-д���ܹ���
 *     д������ԭ��+��ռ  ִ��ʱ��֤������ͳһ�壬�м䲻�ܱ��ָ�
 */
public class ReadWriteLockDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyCache cache=new MyCache();
		for (int i = 1; i <= 5; i++) {
			final int tempi=i;
			new Thread(() -> {
				try {
					cache.put(tempi+"", tempi+"");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
		
		for (int i = 1; i <= 5; i++) {
			final int tempi=i;
			new Thread(() -> {
				try {
					cache.get(tempi+"");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
			
		
	}
	/**
	 * ������֮ǰ����������ģ�put�����е����ݱ��ָ������ԭ����
3	 ����д�룺
1	 ����д�룺
2	 ����д�룺
4	 ����д�룺
5	 ����д�룺
2	 ���ڶ�ȡ��
1	 ���ڶ�ȡ��
3	 ���ڶ�ȡ��
5	 ���ڶ�ȡ��
4	 ���ڶ�ȡ��
4	 ��ȡ��� null
2	 ��ȡ��� 2
2	 д�����
4	 д�����
	 */
	/**
	 * �Ӷ�д��֮��д�߳�ִ��������һ�����壬ԭ����
3	 ����д�룺
3	 д�����
1	 ����д�룺
1	 д�����
2	 ����д�룺
2	 д�����
4	 ����д�룺
4	 д�����
5	 ����д�룺
5	 д�����
1	 ���ڶ�ȡ��
2	 ���ڶ�ȡ��
	 */

}
