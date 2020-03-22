/**
 * 
 */
package com.nokia.first.singleton;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author yanachen
 * @create 2020-02-06 10:45:22
 * 饿汉式方式不管你用不用，创建类时就直接创建出来了
 * (1)私有构造器，禁止外部创建
 * （2）共有静态实例对象，加final修饰强调是单例，是唯一的，不允许修改
 * (3)静态代码块 随着类的初始化而加载
 */
public class HungrySingleton3 {
	public static final HungrySingleton3 instance;
	public String info;
	static {
		Properties pro=new Properties();
		try {
			pro.load(HungrySingleton3.class.getClassLoader().getResourceAsStream("single.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		instance=new HungrySingleton3(pro.getProperty("info"));
	}
	private HungrySingleton3(String info) {
		this.info=info;
	}
	

	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(HungrySingleton3.instance.getInfo());

	}

}
