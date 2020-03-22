
package com.nokia.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Cache三步骤 1.put 2.get 3.clear 底层实现是Map接口及其实现类，一定要用Volatile可见性，保证修改能及时被通知到
 */
class MyCache{
	volatile Map<String,Object> map=new HashMap<String,Object>();
	ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
	public void put(String key,Object value) {
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t 正在写入：");
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
			System.out.println(Thread.currentThread().getName()+"\t 写入完成");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.writeLock().unlock();
		}
	}
	public void get(String key) {
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t 正在读取：");
			try {
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"\t 读取完成 "+map.get(key));
			
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
 * 多个线程同时读一个资源类没有问题，为了满足并发量，读取共享资源应该可以同时进行。
 * 但是有一个线程想去写共享资源，就不应该再有其他线程可以对该资源进行读或者写
 * 小总结：
 *     读-读能共存
 *     读-写不能共存
 *     写-写不能共存
 *     写操作：原子+独占  执行时保证完整的统一体，中间不能被分隔
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
	 * 不加锁之前是这样输出的，put方法中的内容被分割，不符合原子性
3	 正在写入：
1	 正在写入：
2	 正在写入：
4	 正在写入：
5	 正在写入：
2	 正在读取：
1	 正在读取：
3	 正在读取：
5	 正在读取：
4	 正在读取：
4	 读取完成 null
2	 读取完成 2
2	 写入完成
4	 写入完成
	 */
	/**
	 * 加读写锁之后：写线程执行完整的一个整体，原子性
3	 正在写入：
3	 写入完成
1	 正在写入：
1	 写入完成
2	 正在写入：
2	 写入完成
4	 正在写入：
4	 写入完成
5	 正在写入：
5	 写入完成
1	 正在读取：
2	 正在读取：
	 */

}
