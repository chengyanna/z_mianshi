/**
 * 
 */
/**
 * @author yanachen
 * @create 2020-02-06 10:31:01
 * 02-�������ģʽ
 * JVM��Runtime���õľ��ǵ���ģʽ,����ģʽ
 * private static Runtime currentRuntime = new Runtime();
    public static Runtime getRuntime() {
        return currentRuntime;
    }
    private Runtime() {}
 * ����ģʽ���ֳ�����ʽ
 * ������ʽ:ֱ�Ӵ������󣬲������̰߳�ȫ����
 *   1.ֱ��ʵ��������ʽ�����ֱ�ۣ�
 *   2.ö��ֵ�����ࣩ
 *   3.��̬��������ʽ���ʺϸ���ʵ������
 * 
 *  ������ʽ
 *    1.�̲߳���ȫ�����õ��̣߳�
 *    2.�̰߳�ȫ�����ö��̣߳�
 *    3.˽�о�̬�ڲ�����ʽ�����ö��̣߳�
 * 
 */
package com.nokia.first.singleton;
