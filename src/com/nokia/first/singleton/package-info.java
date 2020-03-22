/**
 * 
 */
/**
 * @author yanachen
 * @create 2020-02-06 10:31:01
 * 02-单例设计模式
 * JVM中Runtime类用的就是单例模式,饿汉模式
 * private static Runtime currentRuntime = new Runtime();
    public static Runtime getRuntime() {
        return currentRuntime;
    }
    private Runtime() {}
 * 单例模式几种常见形式
 * 。饿汉式:直接创建对象，不存在线程安全问题
 *   1.直接实例化饿汉式（简洁直观）
 *   2.枚举值（最简洁）
 *   3.静态代码块饿汉式（适合复杂实例化）
 * 
 *  。懒汉式
 *    1.线程不安全（适用单线程）
 *    2.线程安全（适用多线程）
 *    3.私有静态内部类形式（适用多线程）
 * 
 */
package com.nokia.first.singleton;
