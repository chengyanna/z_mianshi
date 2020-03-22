/**
 * 
 */
package com.nokia.alibaba;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * @author yanachen
 * ��ǿ�ơ�SimpleDateFormat ���̲߳���ȫ���࣬һ�㲻Ҫ����Ϊstatic�������������Ϊstatic�����������
 * ����ʹ��DateUtils�����ࡣ ������ע���̰߳�ȫ��ʹ��DateUtils�����Ƽ����´���
private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
@Override
protected DateFormat initialValue() {
return new SimpleDateFormat("yyyy-MM-dd");
}
};

org.apache.commons.lang3.time.DateUtils dateUtils=new DateUtils();
		dateUtils.parseDate(str, parsePatterns);
˵���������JDK8��Ӧ�ã�����ʹ��Instant����Date��LocalDateTime����Calendar��DateTimeFormatter����SimpleDateFormat���ٷ������Ľ��ͣ�simple beautiful strong immutable thread-safe��
 *
 */
public class SimpleDataFormatUnsafeDemo {
	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//1.ThreadLocal��ʽ,����DateFormatֵ��Ȼ���ʽ��
//		SimpleDataFormatUnsafeDemo.df.set(value);
		System.out.println(SimpleDataFormatUnsafeDemo.df.get().format(new Date()));
		//2.DateUtils��ʽ

	}

}
