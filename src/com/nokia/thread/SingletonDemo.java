package com.nokia.thread;

/**
 * ����ģʽ������ 1��newһ��static������ֵΪnull 2.�յĹ��췽�� 3.getInstance
 *
 */
public class SingletonDemo {
	private static volatile SingletonDemo instance=null;
	private SingletonDemo() {
		System.out.println("SingletonDemo was created!!!");
	}
	
//	public static SingletonDemo getInstance() {
//		if(instance==null)
//			instance=new SingletonDemo();
//		return instance;
//	}
	//DCL (Double Check Lock˫�˼�������) new SingletonDemo()
	//memory=allocate();//1.�����ڴ�ռ�
	//instance(memory);//2.��ʼ������
	//instance=memory;//3.����instance��ָ��շ�����ڴ��ַ����ʱinstance!=null
	//ָ�����ſ��ܳ���1,3,2, instance!=null���ǻ�û��ɳ�ʼ��������returnһ��δ��ʼ���Ķ����̲߳���ȫ������Ҫ����volatile
	public static SingletonDemo getInstance() {
		if(instance==null)
		{
			synchronized(SingletonDemo.class) {
				if(instance==null)					
					instance=new SingletonDemo();
			}
		}
		return instance;
	}
	public static void main(String[] args) {
		//���߳��µĲ���
//		SingletonDemo was created!!!����ӡ��һ��
//		true
//		true
//		true
//		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//		System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//		
//		//���߳��µĲ���
		//����20���߳� SingletonDemo was created!!!����ӡ��N��Σ���ȷ��
		//���������1.��getInstance��������synchronized,̫���ˣ�����һ���ˣ��������ܽ����� 2.DCL+volatile
		for(int i=1;i<=20;i++) {
			new Thread(()->{
				SingletonDemo.getInstance();				
			},String.valueOf(i)).start();
			
		}

	}

}
