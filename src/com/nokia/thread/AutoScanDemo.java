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
 * Ԥ����Ҫɨ������豸��������online�ģ�������control PC,�����ܵ�¼�ɹ�(����password list)
 * nmap -iL "+input+" -p3389,23,5003,5030 -oN "+output
 * --iL ��һ���ļ��е���ɨ���ַ��������ɨ��
 * --oN output scan result to normal file
 *
 *SNMP�ǻ���udpЭ�鷢�ͱ��ĵģ���snmp�˿�Ĭ��Ϊ161 ,��"udp:"+ip+"/161" 
 *1����ʼ��snmp,����������
 4             initSnmp();
 5             //2������Ŀ�����
 6             Target target = createTarget(SnmpConstants.version2c, SnmpConstants.DEFAULT_COMMAND_RESPONDER_PORT);
 7             //3����������
 8             PDU pdu = createPDU(SnmpConstants.version2c, PDU.GET, oid);
 9             System.out.println("-------> ����PDU <-------"); 
10             //4�����ͱ��ģ�����ȡ���ؽ��
11             ResponseEvent responseEvent = snmp.send(pdu, target);
12             PDU response = responseEvent.getResponse();
13             System.out.println("���ؽ����" + response);
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
			//�̳߳�callable�ӿڣ�����sshִ�н����
			
			//����������ִ����ϣ���ʼִ�л��ܼ��㣬�������ݿ��
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			executorService.shutdown();
		} 

	}

}
