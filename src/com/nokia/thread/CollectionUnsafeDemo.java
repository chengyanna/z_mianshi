/**
 * 
 */
package com.nokia.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanachen
 * 集合类不安全问题
 * ArrayList 其add方法不是synchronized,多线程环境下可能会不安全
 * Map
 * Set
 */
public class CollectionUnsafeDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Map<String,String> list=new HashMap<String,String>(); //HashMap，默认初始化容量是16，负载因子0.75
//		Map<String,String> list=Collections.synchronizedMap(new HashMap<String,String>()); //add方法是synchronized
		Map<String,String> list=new ConcurrentHashMap<String,String>(); //put方法里有synchronized语句块
		for(int i=1;i<=30;i++) {
			new Thread(()->{			
				list.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
				
			},String.valueOf(i)).start();
		}

       /*
        * 1.故障现象 java.util.ConcurrentModificationException
        * 2.导致原因
                      *          并发争抢修改导致异常 
        * 3.解决方案        
        *   3.1 Collections.synchronizedMap(new ArrayList<String>()) 辅助工具类
        *   3.2 new ConcurrentHashMap<String,String>(); 	    
        * 4.优化建议，避免以后再发生
        * */

	}
	public static void setNotSafe() {
//		Set<String> list=new HashSet<String>(); //HashSet的底层实现是HashMap，默认初始化容量是16，负载因子0.75,Set的值为key，value是一个常量Object
//		Set<String> list=Collections.synchronizedSet(new HashSet<String>()); //add方法是synchronized
		Set<String> list=new CopyOnWriteArraySet<String>(); //默认初始化一个长度为0的Object[],写时复制,本质上是CopyOnWriteArrayList
		for(int i=1;i<=30;i++) {
			new Thread(()->{			
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
				
			},String.valueOf(i)).start();
		}

       /*
        * 1.故障现象 java.util.ConcurrentModificationException
        * 2.导致原因
                      *          并发争抢修改导致异常 
        * 3.解决方案        
        *   3.1 Collections.synchronizedList(new ArrayList<String>()) 辅助工具类
        *   3.2 new CopyOnWriteArraySet<String>(); 写时复制， 读写分离思想,本质是CopyOnWriteArrayList
        *   public CopyOnWriteArraySet() {
        		al = new CopyOnWriteArrayList<E>();
    		}
        * /**
	     * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
	     * default initial capacity (16) and load factor (0.75).
	     *
	    public HashSet() {
	        map = new HashMap<>();
	    }  
	    public boolean add(E e) {
        	return map.put(e, PRESENT)==null;
    	} 
    	 private static final Object PRESENT = new Object(); 
        * 4.优化建议，避免以后再发生
        * */

	}
	public static void listNotSafe() {
//		List<String> list=new ArrayList<String>(); //默认初始化一个长度为10的Object[]
//		List<String> list=new Vector<String>(); //默认初始化一个长度为10的Object[],add方法是synchronized
//		List<String> list=Collections.synchronizedList(new ArrayList<String>()); //默认初始化一个长度为10的Object[],add方法是synchronized
		List<String> list=new CopyOnWriteArrayList<String>(); //默认初始化一个长度为0的Object[],写时复制
		for(int i=1;i<=30;i++) {
			new Thread(()->{			
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
				
			},String.valueOf(i)).start();
		}

       /*
        * 1.故障现象 java.util.ConcurrentModificationException
        * 2.导致原因
                      *          并发争抢修改导致异常 
        * 3.解决方案
        *   3.1 new Vector, 实现了List接口
        *   3.2 Collections.synchronizedList(new ArrayList<String>()) 辅助工具类
        *   3.3 new CopyOnWriteArrayList<String>(); 写时复制， 读写分离思想
        *   public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }
        * 4.优化建议，避免以后再发生
        * */

	}

}
