/**
 * 
 */
package com.nokia.jvm;

/**
 * @author yanachen
 * @create 2020-02-01 19:56:06
 * 实际工作中，如何结合SpringBoot进行调优，如何落地？
 * JVMGC》》调优 》》springboot微服务的生产部署和调参优化
 * 
 * 1.开发微服务工程
 * 2.mvn clean package 
 * 3.微服务启动的时候同时配置JVM/GC调优参数
 * 4.公式 java -server jvm的各种参数  -jar 第一步生成的jar包/war包 (以server模式启动java vm)
 * 如 java -server -Xms1024m -Xmx1024 -XX:+UseG1GC hello.war
 */
public class JVMSpringBootDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
