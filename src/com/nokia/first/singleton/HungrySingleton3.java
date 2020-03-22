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
 * ����ʽ��ʽ�������ò��ã�������ʱ��ֱ�Ӵ���������
 * (1)˽�й���������ֹ�ⲿ����
 * ��2�����о�̬ʵ�����󣬼�final����ǿ���ǵ�������Ψһ�ģ��������޸�
 * (3)��̬����� ������ĳ�ʼ��������
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
