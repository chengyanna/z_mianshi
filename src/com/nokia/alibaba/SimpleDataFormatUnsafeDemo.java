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
 * 【强制】SimpleDateFormat 是线程不安全的类，一般不要定义为static变量，如果定义为static，必须加锁，
 * 或者使用DateUtils工具类。 正例：注意线程安全，使用DateUtils。亦推荐如下处理：
private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
@Override
protected DateFormat initialValue() {
return new SimpleDateFormat("yyyy-MM-dd");
}
};

org.apache.commons.lang3.time.DateUtils dateUtils=new DateUtils();
		dateUtils.parseDate(str, parsePatterns);
说明：如果是JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替SimpleDateFormat，官方给出的解释：simple beautiful strong immutable thread-safe。
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
		//1.ThreadLocal方式,设置DateFormat值，然后格式化
//		SimpleDataFormatUnsafeDemo.df.set(value);
		System.out.println(SimpleDataFormatUnsafeDemo.df.get().format(new Date()));
		//2.DateUtils方式

	}

}
