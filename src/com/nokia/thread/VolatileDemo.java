package com.nokia.thread;

import java.util.concurrent.atomic.AtomicInteger;

class MyData{
	volatile int number=0;
	public void addTo60(){
		this.number=60;
	}
	public void addPlusPlus() {
		this.number++;
	}
	
	AtomicInteger atomicInteger=new AtomicInteger();
	public void addMyAtomic() {
		atomicInteger.getAndIncrement();
	}
}

public class VolatileDemo {
	

	/**
	 * 1. ��֤Volatile�ɼ���
	 * 1.1����numberû��Volatile�ؼ��֣�û�пɼ��ԣ����һ�仰ʼ�մ�ӡ�����
	 * 1.2���numberǰ������volatile�ؼ��֣�Main�߳��ܼ�ʱ��֪AAA�߳��޸ĺ��ֵ���ɼ���,���һ�仰���Դ�ӡ����
	 * 
	 * 2.��֤volatile����֤ԭ����
	 *  2.1��Ϊԭ���ԣ����������ԣ�һ���ԣ��̲߳����������߷ָ���Ҫôȫ���ɹ���Ҫôȫ��ʧ��
	 *  2.2����֤ԭ���԰���
	 *  2.3 why number++���̲߳���ȫ�ģ��п���д���ǣ� number++ʵ��������������a)get fieldȡֵ b)number+1 3)put fieldд�����ڴ�, ��һ���߳�ǰ������ɣ���׼��put fieldʱ���̱߳����𣬴�ʱ����һ���߳��޸���number��ֵ��Ȼ��û������ȡ���µ�ֵ��put field��
	 *  2.4 ��ν�� a)��synchronized b)ʹ��juc�µ�AtomicInteger �ײ�ʵ��ԭ����CAS
	 */
	public static void main(String[] args) {
		MyData myData=new MyData();
		
		//����20���̣߳�ÿ���߳�i++ 1000��, ����number��ֵӦ��Ϊ20000����ʵ����ÿ��ִ�н�����ܶ���ͬ
		for(int i=1;i<=20;i++) {
			for(int j=1;j<=1000;j++) {
				
				new Thread(()->{
					myData.addPlusPlus();
					myData.addMyAtomic();
					
				},String.valueOf(i)).start();
			}
		}
		
		//��Ҫ�ȴ�����20���߳�ȫ��������ɺ�����main�߳�ȡ�����յļ�����ֵ
		while(Thread.activeCount()>2) {
			Thread.yield();//�߳��˳�,����ռ��CPU
		}
		System.out.println("int final value is :"+myData.number);
		System.out.println("AtomicInteger final value is :"+myData.atomicInteger);
		

	}
	//��֤�ɼ��԰���
	public static void seeOkByVolatile() {
		MyData myData=new MyData();
		
		//�߳�AAA
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+" come in");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myData.addTo60();
			System.out.println(Thread.currentThread().getName()+" updated number value:"+myData.number);
			
		},"AAA").start();
		
		//main���߳�
		while(myData.number==0) {
			//ֻҪΪ0��һֱ�ȴ�
		}
		System.out.println(Thread.currentThread().getName()+" mission is over!main get number value:"+myData.number);
		
		
	}

}
