/**
 * 
 */
package com.nokia.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yanachen
 * @create 2020-02-10 10:35:36
 * ==�ȿ��ԱȽϻ����������ͣ������ԱȽ����ã����ñȽϵ��ǵ�ַ
 * new�����Ķ���==��false
 * 
 * equals�ȽϵĶ�����ȷ��������û����д����Object��equals Ĭ����==
 * public boolean equals(Object obj) {
        return (this == obj);
    }
    
 * equalsҪ��hashcodeҪͬʱ��д
 * 
 * HashSet�ײ�ʵ����HashMap, key��Element��value��Object�������������ظ��� HashMap�Ƚ��Ƿ��ظ����жϵ���hashcode�����������������֤�űȽ�
 */
public class TestEqualsDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1="abc";//�ڳ�������������һ��
		String s2=new String("abc");//���ڶ���
		String s3="abc";//�ӳ�������ȡ
		String s4="xxx";
		String s5="abc"+"xxx";
		String s6=s3+s4;//+�������µĶ���
		String s7=s3+s4;//+�������µĶ���
		
		System.out.println(s1==s2);//false
		System.out.println(s1==s5);//false
		System.out.println(s1==s6);//false
		System.out.println(s1==s6.intern());//false
		System.out.println(s2==s2.intern());//false����intern()����ʱ���������������ں͸��ַ���equals��ֵ����ֱ�Ӵӳ�����ȡ��������������ڣ���������������string,Ȼ�󷵻ض��������
		                                    //s2�ڶ��s2.intern()�ڳ����أ����Ե�ַ����ȣ�== false
		System.out.println(s1==s2.intern());//true s1,s2.intern()���ڳ��������ֵ��ȣ�����true
		System.out.println(s5==s6);//false
		System.out.println(s6==s7);//false
		System.out.println(s5==s6.intern());
		/**
	     * Returns a canonical representation for the string object.
	     * <p>
	     * A pool of strings, initially empty, is maintained privately by the
	     * class {@code String}.
	     * <p>
	     * When the intern method is invoked, if the pool already contains a
	     * string equal to this {@code String} object as determined by
	     * the {@link #equals(Object)} method, then the string from the pool is
	     * returned. Otherwise, this {@code String} object is added to the
	     * pool and a reference to this {@code String} object is returned.
	     * */

	}

}
