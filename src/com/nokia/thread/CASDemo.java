package com.nokia.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanachen
 * ��ΪCAS  �Ƚϲ����� CompareAndSet
 *
 */
public class CASDemo {

	public static void main(String[] args) {
		//�������ڴ������ֵΪ5
		AtomicInteger atomicInteger=new AtomicInteger(5);
		
		//main�߳��޸Ĺ����ڴ��б�����ֵΪ2019������ͼд���������ڴ棬д��֮ǰ�ȱȽϣ���������ֵ��5�����������ڴ��еı���û�б��޸Ĺ�
		System.out.println(atomicInteger.compareAndSet(5, 2019)+" atomicInteger="+atomicInteger.get());
		
		//��������һ���߳�Ҳ��ͼ�޸ı�����ֵ,û���޸ĳɹ�����Ϊ�������ڴ��ֵ�ı���
		System.out.println(atomicInteger.compareAndSet(5, 1024)+" atomicInteger="+atomicInteger.get());

		//result:
//		true atomicInteger=2019
//		false atomicInteger=2019
		
		atomicInteger.getAndIncrement();
	}

}
