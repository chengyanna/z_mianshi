package com.nokia.nio;

import java.nio.ByteBuffer;

import org.junit.Test;

/*
 * Files.copy
 * �й��javaѧϰ�ʼǡ���NIO��New IO�� https://blog.csdn.net/zxm1306192988/article/details/60581173
 * һ����������Buffer������java NIO �и������ݵĴ洢���������������顣���ڴ洢��ͬ���͵����ݡ�
 * 
 * �����������͵Ĳ�ͬ(boolean ����)�������� Buffer �������ࣺ
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 
 * �����������Ĺ���ʽ����һ�£�ͨ��allocate()��ȡ������
 * 
 * ������������ȡ���ݵ��������ķ�����
 * put():�������ݵ���������
 *       put(byte b)�������������ֽ�д�뻺�����ĵ�ǰλ��
 *       put(byte[] src)���� src �е��ֽ�д�뻺�����ĵ�ǰλ��
 *       put(int index, byte b)����ָ���ֽ�д�뻺����������λ��(�����ƶ� position)
 * get():��ȡ�������е�����
 *       get() ����ȡ�����ֽ�
 *       get(byte[] dst)��������ȡ����ֽڵ� dst ��
 *       get(int index)����ȡָ������λ�õ��ֽ�(�����ƶ� position)
 *       
 * �����������е��ĸ��������ԣ�
 * capacity����������ʾ�����������洢���ݵ�������һ���������ܸı䡣
 * limit�����ޣ���ʾ�������п��Բ������ݵĴ�С��(limit�����ݲ��ܽ��ж�д)
 * position��λ�ã���ʾ�����������ڲ������ݵ�λ�á�
 * mark:��ǣ���ʾ��¼��ǰpositionλ�á�����ͨ��reset()�ָ���mark��λ�á�
 * 
 * 0<=mark<=position<=limit<=capacity
 * 
 * �ġ�ֱ�ӻ��������ֱ�ӻ�������
 * ��ֱ�ӻ�������ͨ��allocate()�������仺��������������������JVM���ڴ��С�
 *            
 * ֱ�ӻ�������ͨ��allocateDirect()��������ֱ�ӻ��������������������������ڴ��С��������Ч��
 *          �˷������ص� ���������з����ȡ����������ɱ�ͨ�����ڷ�ֱ�ӻ����� ��
 *          ֱ�ӻ����������ݿ���פ���ڳ�����������ն�֮��.
 *          ��ֱ�ӻ�������Ҫ�������Щ���ܻ���ϵͳ�ı��� I/O ����Ӱ��Ĵ��͡��־õĻ�������
 *          ��ý���ֱ�ӻ��������ڳ������ܷ���������Ժô�ʱ�������ǡ�
 *          ֱ���ֽڻ����������Թ� ͨ��FileChannel �� map() ���� ���ļ�����ֱ��ӳ�䵽�ڴ��������� ���÷�������MappedByteBuffe
 */
public class TestBuffer {
    @Test
    public void test1(){
        String str="abcde";

        //1.����һ��ָ����С�Ļ�����
        ByteBuffer buf=ByteBuffer.allocate(1024);

        System.out.println("--------------allocate()----------------");
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//1024
        System.out.println(buf.capacity());//1024

        //2.����put()������ݵ���������
        buf.put(str.getBytes());

        System.out.println("-------------put()-------------");
        System.out.println(buf.position());//5
        System.out.println(buf.limit());//1024
        System.out.println(buf.capacity());//1024

        //3.�л���ȡ����ģʽ
        buf.flip();
        System.out.println("--------------flip()------------");
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//5
        System.out.println(buf.capacity());//1024

        //4.����get()��ȡ�������е�����
        byte[] dst=new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst,0,dst.length));//abcd

        System.out.println("--------------get()------------");
        System.out.println(buf.position());//5
        System.out.println(buf.limit());//5
        System.out.println(buf.capacity());//1024

        //5.rewind():���ظ���
        buf.rewind();

        System.out.println("--------------rewind()------------");
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//5
        System.out.println(buf.capacity());//1024

        //6.clear():��ջ����������ǻ������е�������Ȼ���ڣ����Ǵ��ڡ���������״̬
        buf.clear();

        System.out.println("--------------clear()------------");
        System.out.println(buf.position());//0
        System.out.println(buf.limit());//1024
        System.out.println(buf.capacity());//1024

        System.out.println((char)buf.get());
    }

    @Test
    public void test2(){
        String str="abcde";

        ByteBuffer buf=ByteBuffer.allocate(1024);

        buf.put(str.getBytes());

        buf.flip();

        byte[] dst=new byte[buf.limit()];
        buf.get(dst,0,2);
        System.out.println(new String(dst,0,2));//ab
        System.out.println(buf.position());//2

        //mark():���
        buf.mark();

        buf.get(dst,2,2);//�ٶ�����λ��
        System.out.println(new String(dst, 2, 2));//cd
        System.out.println(buf.position());//4

        //reset():�ָ���mark��λ��
        buf.reset();
        System.out.println(buf.position());//2

        //�жϻ��������Ƿ���ʣ������
        if(buf.hasRemaining()){
            //��ȡ�������п��Բ���������
            System.out.println(buf.remaining());//3
        }
    }

    @Test
    public void test3(){
        //����ֱ�ӻ�����
        ByteBuffer buf=ByteBuffer.allocate(1024);
        System.out.println(buf.isDirect());//false
    }
}
