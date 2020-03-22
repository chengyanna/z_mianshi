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
 * �����಻��ȫ����
 * ArrayList ��add��������synchronized,���̻߳����¿��ܻ᲻��ȫ
 * Map
 * Set
 */
public class CollectionUnsafeDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Map<String,String> list=new HashMap<String,String>(); //HashMap��Ĭ�ϳ�ʼ��������16����������0.75
//		Map<String,String> list=Collections.synchronizedMap(new HashMap<String,String>()); //add������synchronized
		Map<String,String> list=new ConcurrentHashMap<String,String>(); //put��������synchronized����
		for(int i=1;i<=30;i++) {
			new Thread(()->{			
				list.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
				
			},String.valueOf(i)).start();
		}

       /*
        * 1.�������� java.util.ConcurrentModificationException
        * 2.����ԭ��
                      *          ���������޸ĵ����쳣 
        * 3.�������        
        *   3.1 Collections.synchronizedMap(new ArrayList<String>()) ����������
        *   3.2 new ConcurrentHashMap<String,String>(); 	    
        * 4.�Ż����飬�����Ժ��ٷ���
        * */

	}
	public static void setNotSafe() {
//		Set<String> list=new HashSet<String>(); //HashSet�ĵײ�ʵ����HashMap��Ĭ�ϳ�ʼ��������16����������0.75,Set��ֵΪkey��value��һ������Object
//		Set<String> list=Collections.synchronizedSet(new HashSet<String>()); //add������synchronized
		Set<String> list=new CopyOnWriteArraySet<String>(); //Ĭ�ϳ�ʼ��һ������Ϊ0��Object[],дʱ����,��������CopyOnWriteArrayList
		for(int i=1;i<=30;i++) {
			new Thread(()->{			
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
				
			},String.valueOf(i)).start();
		}

       /*
        * 1.�������� java.util.ConcurrentModificationException
        * 2.����ԭ��
                      *          ���������޸ĵ����쳣 
        * 3.�������        
        *   3.1 Collections.synchronizedList(new ArrayList<String>()) ����������
        *   3.2 new CopyOnWriteArraySet<String>(); дʱ���ƣ� ��д����˼��,������CopyOnWriteArrayList
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
        * 4.�Ż����飬�����Ժ��ٷ���
        * */

	}
	public static void listNotSafe() {
//		List<String> list=new ArrayList<String>(); //Ĭ�ϳ�ʼ��һ������Ϊ10��Object[]
//		List<String> list=new Vector<String>(); //Ĭ�ϳ�ʼ��һ������Ϊ10��Object[],add������synchronized
//		List<String> list=Collections.synchronizedList(new ArrayList<String>()); //Ĭ�ϳ�ʼ��һ������Ϊ10��Object[],add������synchronized
		List<String> list=new CopyOnWriteArrayList<String>(); //Ĭ�ϳ�ʼ��һ������Ϊ0��Object[],дʱ����
		for(int i=1;i<=30;i++) {
			new Thread(()->{			
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
				
			},String.valueOf(i)).start();
		}

       /*
        * 1.�������� java.util.ConcurrentModificationException
        * 2.����ԭ��
                      *          ���������޸ĵ����쳣 
        * 3.�������
        *   3.1 new Vector, ʵ����List�ӿ�
        *   3.2 Collections.synchronizedList(new ArrayList<String>()) ����������
        *   3.3 new CopyOnWriteArrayList<String>(); дʱ���ƣ� ��д����˼��
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
        * 4.�Ż����飬�����Ժ��ٷ���
        * */

	}

}
