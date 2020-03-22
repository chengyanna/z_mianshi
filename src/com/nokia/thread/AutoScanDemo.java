/**
 * 
 */
package com.nokia.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanachen * 
 * ip range list:
 * 10.44.168.253/24
 * 10.66.10.93/24
 * 
 * 预计需要扫描多少设备，多少是online的，多少是control PC,多少能登录成功(根据password list)
 * nmap -iL "+input+" -p3389,23,5003,5030 -oN "+output
 * --iL 从一个文件中导入扫描地址，并进行扫描
 * --oN output scan result to normal file
 *
 *SNMP是基于udp协议发送报文的，且snmp端口默认为161 ,如"udp:"+ip+"/161" 
 *1、初始化snmp,并开启监听
 4             initSnmp();
 5             //2、创建目标对象
 6             Target target = createTarget(SnmpConstants.version2c, SnmpConstants.DEFAULT_COMMAND_RESPONDER_PORT);
 7             //3、创建报文
 8             PDU pdu = createPDU(SnmpConstants.version2c, PDU.GET, oid);
 9             System.out.println("-------> 发送PDU <-------"); 
10             //4、发送报文，并获取返回结果
11             ResponseEvent responseEvent = snmp.send(pdu, target);
12             PDU response = responseEvent.getResponse();
13             System.out.println("返回结果：" + response);
 */

class ScanTask implements Callable<Integer>{
	private String ip;
	private CountDownLatch countDownLatch;
	public ScanTask(String ip,CountDownLatch countDownLatch) {
		super();
		this.ip = ip;
		this.countDownLatch=countDownLatch;
	}

	@Override
	public Integer call() throws Exception {
		//nmap scan
		int ipcount=ThreadLocalRandom.current().nextInt(10);				
		System.out.println(Thread.currentThread().getName()+"\t "+ipcount);
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		countDownLatch.countDown();
		return ipcount;
	}
	
}
public class AutoScanDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CountDownLatch countDownLatch=new CountDownLatch(10);
		ExecutorService executorService=Executors.newFixedThreadPool(10);
		CopyOnWriteArrayList<Future<Integer>> list=new CopyOnWriteArrayList<Future<Integer>>();
		
		try {
			for (int i = 1; i <= 10; i++) {
				list.add(executorService.submit(new ScanTask(i+"",countDownLatch)));
			}
			
			countDownLatch.await();
			
			int total=0;
			int result=0;
			for (int i = 1; i < 10; i++) {
				result=list.get(i).get();
				total+=result;
			}
			System.out.println("total: "+total);
			
			//foreach online pc, try to ssh 
			//线程池callable接口，返回ssh执行结果，
			
			//等所有任务执行完毕，开始执行汇总计算，插入数据库等
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			executorService.shutdown();
		} 

	}

}
