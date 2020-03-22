/**
 * 
 */
package com.nokia.jvm.ref;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author yanachen
 * WeekHashMap位于java.util包下
 *
 */
public class WeakMapDemo {
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		myHashMap();
		System.out.println("============================");
		weakHashMap();
	}

	private static void weakHashMap() {		
		WeakHashMap<Integer,String> map=new WeakHashMap<>();
		Integer key=new Integer(2);
		String value="WeakHashMap";
		map.put(key, value); //底层实现是Node<K,V>
		System.out.println(map);
		
		key=null;		
		System.out.println(map);//key设置为null,不影响map的值	
		
		System.gc();
		System.out.println(map+"\t"+map.size());
	}

	private static void myHashMap() {
		Map<Integer,String> map=new HashMap<Integer,String>();
		Integer key=new Integer(1);
		String value="MyHashMap";
		map.put(key, value); //底层实现是Node<K,V>
		System.out.println(map);
		
		key=null;
		System.out.println(map);//key设置为null,不影响map的值	
		
		System.gc();
		System.out.println(map);
		
	}

}
