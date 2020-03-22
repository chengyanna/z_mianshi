/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 22:51:54
 * 1.SpringMVC����ν��POST���������������⣬Get������δ�����
 * Post������web.xml������filter Spring�ṩ��org.springframework.web.filter.CharacterEncodingFilter, ָ������encodingΪUTF-8
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
	
 * Get�����޸�tomcat server.xml��Connector ������URIEncoding="UTF-8"
 */
public class SpringMVC_09 {

}
