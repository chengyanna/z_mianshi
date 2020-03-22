/**
 * 
 */
package com.nokia.first;

/**
 * @author yanachen
 * @create 2020-02-06 21:59:51
 * spring bean scope来定义作用域
 * singleton 单例    默认，IOC容器一创建就实例化Bean对象
 * prototype 原型  每次调用getBean()都会返回一个新的实例
 * request 每次http 请求都会返回一个新的bean,该作用域仅适用于WebApplicationContext环境
 * session 同一个http session共享一个Bean,不同的http session使用不同的Bean.该作用域仅适用于WebApplicationContext环境
 */
public class SSM_SpringBeanScope_07 {

}
