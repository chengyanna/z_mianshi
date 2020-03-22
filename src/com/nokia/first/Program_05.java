/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 18:04:24
 * ��̬�滮
 * ����⣺��n��̨�ף�һ��ֻ����1������2�������ж������߷���
 * 1.�ݹ�  �ݹ�̫�����ʱ�䳤���������ջ���
 * ��n=1  ->һ��                                              f(1)=1
 * ��n=2  ->(1)һ��һ�� ��2��ֱ��2��     f(2)=2
 * ��n=3  ->�ȵ���f(1),Ȼ���f(1)ֱ�ӿ�2��     f(3)=f(1)+f(2)  
 *        ->�ȵ���f(2)��Ȼ���f(2)ֱ�ӿ�1�� 
 * ��n=4  ->�ȵ���f(2),Ȼ���f(2)ֱ�ӿ�2��     f(4)=f(2)+f(3) 
 *        ->�ȵ���f(3)��Ȼ���f(3)ֱ�ӿ�1�� 
 * ��n=x  ->�ȵ���f(x-2),Ȼ���f(x-2)ֱ�ӿ�2��    
 *        ->�ȵ���f(x-1)��Ȼ���f(x-1)ֱ�ӿ�1��  f(x)=f(x-2)+f(x-1)
 * 2.ѭ������  ��ʡ�ռ�
 * ��n=1  ->һ��                                              f(1)=1  
 * ��n=2  ->(1)һ��һ�� ��2��ֱ��2��     f(2)=2  
 * ��n=3  ->�ȵ���f(1),Ȼ���f(1)ֱ�ӿ�2��     f(3)=f(1)+f(2)  f(3)=ONE+TWO=f(2)+f(1)
 *        ->�ȵ���f(2)��Ȼ���f(2)ֱ�ӿ�1�� 
 * ��n=4  ->�ȵ���f(2),Ȼ���f(2)ֱ�ӿ�2��     f(4)=f(3)+f(2) TWO=ONE=f(2) ONE=f(3) f(4)=ONE+TWO
 *        ->�ȵ���f(3)��Ȼ���f(3)ֱ�ӿ�1�� 
 * ��n=x  ->�ȵ���f(x-2),Ȼ���f(x-2)ֱ�ӿ�2��    
 *        ->�ȵ���f(x-1)��Ȼ���f(x-1)ֱ�ӿ�1��  f(x)=f(x-2)+f(x-1) f(x)=ONE+TWO
 */
public class Program_05 {
	public int step(int n) {
		if(n<1) {
			throw new IllegalStateException();
		}
		if(n==1 ||n==2) {
			return n;
		}		
		return step(n-2)+step(n-1);
	}
	
	public int loop(int n) {
		if(n<1) {
			throw new IllegalStateException();
		}
		if(n==1 ||n==2) {
			return n;
		}	
		int one=2;//����һ������
		int two=1;//������������
		int sum=0;
		for(int i=3;i<=n;i++) {
			sum=one+two;
			two=one;
			one=sum;			
		}
		return sum;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Program_05 p=new Program_05();
		System.out.println(p.step(40));
		System.out.println(p.loop(40));
	}

}
