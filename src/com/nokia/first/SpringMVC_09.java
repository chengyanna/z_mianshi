/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 22:51:54
 * 1.SpringMVC中如何解决POST请求中文乱码问题，Get的又如何处理呢
 * Post请求：在web.xml中配置filter Spring提供了org.springframework.web.filter.CharacterEncodingFilter, 指明参数encoding为UTF-8
 * <filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>ForceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	
 * Get请求：修改tomcat server.xml中Connector 中设置URIEncoding="UTF-8"
 */
public class SpringMVC_09 {

}
