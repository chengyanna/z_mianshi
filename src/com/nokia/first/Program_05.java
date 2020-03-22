/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 18:04:24
 * 动态规划
 * 编程题：有n步台阶，一次只能上1步或者2步，共有多少种走法？
 * 1.递归  递归太深，运行时间长，容易造成栈溢出
 * 。n=1  ->一步                                              f(1)=1
 * 。n=2  ->(1)一步一步 （2）直接2步     f(2)=2
 * 。n=3  ->先到达f(1),然后从f(1)直接跨2步     f(3)=f(1)+f(2)  
 *        ->先到达f(2)，然后从f(2)直接跨1步 
 * 。n=4  ->先到达f(2),然后从f(2)直接跨2步     f(4)=f(2)+f(3) 
 *        ->先到达f(3)，然后从f(3)直接跨1步 
 * 。n=x  ->先到达f(x-2),然后从f(x-2)直接跨2步    
 *        ->先到达f(x-1)，然后从f(x-1)直接跨1步  f(x)=f(x-2)+f(x-1)
 * 2.循环迭代  节省空间
 * 。n=1  ->一步                                              f(1)=1  
 * 。n=2  ->(1)一步一步 （2）直接2步     f(2)=2  
 * 。n=3  ->先到达f(1),然后从f(1)直接跨2步     f(3)=f(1)+f(2)  f(3)=ONE+TWO=f(2)+f(1)
 *        ->先到达f(2)，然后从f(2)直接跨1步 
 * 。n=4  ->先到达f(2),然后从f(2)直接跨2步     f(4)=f(3)+f(2) TWO=ONE=f(2) ONE=f(3) f(4)=ONE+TWO
 *        ->先到达f(3)，然后从f(3)直接跨1步 
 * 。n=x  ->先到达f(x-2),然后从f(x-2)直接跨2步    
 *        ->先到达f(x-1)，然后从f(x-1)直接跨1步  f(x)=f(x-2)+f(x-1) f(x)=ONE+TWO
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
		int one=2;//最后跨一步到达
		int two=1;//最后跨两步到达
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
