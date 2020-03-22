/**
 * 
 */
package com.nokia.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

/**
 * @author yanachen
 * @create 2020-03-19 16:29:38
 */
/*
 * һ��ͨ��(Channel):����Դ�ڵ���Ŀ��ڵ�����ӡ���java NIO�и��𻺳��������ݵĴ��䡣Channel�����洢���ݣ���Ҫ��ϻ��������д��䡣
 * 
 * ����ͨ������Ҫʵ����
 *    java.nio.channels.Channel �ӿڣ�
 *        |--FileChannel�����ڶ�ȡ��д�롢ӳ��Ͳ����ļ���ͨ����
 *        |--SocketChannel��ͨ�� TCP ��д�����е����ݡ�
 *        |--ServerSocketChannel�����Լ����½����� TCP ���ӣ���ÿһ���½��������Ӷ��ᴴ��һ�� SocketChannel��
 *        |--DatagramChannel��ͨ�� UDP ��д�����е�����ͨ����
 *        
 * ������ȡͨ��
 * 1.java���֧��ͨ�������ṩ��getChannel()����
 *      ����IO��
 *      FileInputStream/FileOutputStream
 *      RandomAccessFile
 *      
 *      ����IO��
 *      Socket
 *      ServerSocket
 *      DatagramSocket
 *      
 * 2.��JDK 1.7 �е�NIO.2 ��Ը���ͨ���ṩ�˾�̬���� open()
 * 3.��JDK 1.7 �е�NIO.2 ��Files�������newByteChannel()
 * 
 * �ġ�ͨ��֮������ݴ���
 * transferFrom()
 * transferTo()
 * 
 * �塢��ɢ(Scatter)��ۼ�(Gather)
 * ��ɢ��ȡ��Scattering Reads������ͨ���е����ݷ�ɢ�������������
 * �ۼ�д�루Gathering Writes����������������е����ݾۼ���ͨ����
 * 
 * �����ַ�����Charset
 * ���룺�ַ���-���ַ�����
 * ���룺�ַ�����-���ַ���
 */
public class TestChannel {

    //����ͨ������ļ��ĸ���(��ֱ�ӻ�����)
    @Test
    public void test1(){
        long start=System.currentTimeMillis();

        FileInputStream fis=null;
        FileOutputStream fos=null;

        FileChannel inChannel=null;
        FileChannel outChannel=null;
        try{
            fis=new FileInputStream("c:/d/1.mp4");
            fos=new FileOutputStream("c:/d/2.mp4");

            //1.��ȡͨ��
            inChannel=fis.getChannel();
            outChannel=fos.getChannel();

            //2.����ָ����С�Ļ�����
            ByteBuffer buf=ByteBuffer.allocate(1024);

            //3.��ͨ���е����ݴ��뻺������
            while(inChannel.read(buf)!=-1){
                buf.flip();//�л���ȡ���ݵ�ģʽ
                //4.���������е�����д��ͨ����
                outChannel.write(buf);
                buf.clear();//��ջ�����
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(outChannel!=null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel!=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("�ķ�ʱ�䣺"+(end-start));//�ķ�ʱ�䣺1094
    }

    //ʹ��ֱ�ӻ���������ļ��ĸ���(�ڴ�ӳ���ļ�)
    @Test
    public void test2() {
        long start=System.currentTimeMillis();

        FileChannel inChannel=null;
        FileChannel outChannel=null;
        try {
            inChannel = FileChannel.open(Paths.get("c:/d/1.mp4"), StandardOpenOption.READ);
            outChannel=FileChannel.open(Paths.get("c:/d/2.mp4"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

            //�ڴ�ӳ���ļ�
            MappedByteBuffer inMappedBuf=inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf=outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
            //ֱ�ӶԻ������������ݵĶ�д����
            byte[] dst=new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(outChannel!=null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel!=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        long end=System.currentTimeMillis();
        System.out.println("�ķѵ�ʱ��Ϊ��"+(end-start));//�ķѵ�ʱ��Ϊ��200
    }

    //ͨ��֮������ݴ���(ֱ�ӻ�����)
    @Test
    public void test3(){
        long start=System.currentTimeMillis();

        FileChannel inChannel=null;
        FileChannel outChannel=null;
        try {
            inChannel = FileChannel.open(Paths.get("c:/d/1.mp4"), StandardOpenOption.READ);
            outChannel=FileChannel.open(Paths.get("c:/d/2.mp4"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

//            inChannel.transferTo(0, inChannel.size(), outChannel);
            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(outChannel!=null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel!=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("�ķѵ�ʱ��Ϊ��"+(end-start));//�ķѵ�ʱ��Ϊ��147
    }

    //��ɢ�;ۼ�
    @Test
    public void test4(){
        RandomAccessFile raf1=null;
        FileChannel channel1=null;
        RandomAccessFile raf2=null;
        FileChannel channel2=null;
        try {
            raf1=new RandomAccessFile("1.txt","rw");

            //1.��ȡͨ��
            channel1=raf1.getChannel();

            //2.����ָ����С�Ļ�����
            ByteBuffer buf1=ByteBuffer.allocate(100);
            ByteBuffer buf2=ByteBuffer.allocate(1024);

            //3.��ɢ��ȡ
            ByteBuffer[] bufs={buf1,buf2};
            channel1.read(bufs);

            for(ByteBuffer byteBuffer : bufs){
                byteBuffer.flip();
            }
            System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
            System.out.println("--------------------");
            System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));

            //4.�ۼ�д��
            raf2=new RandomAccessFile("2.txt", "rw");
            channel2=raf2.getChannel();

            channel2.write(bufs);

        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(channel2!=null){
                try {
                    channel2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(channel1!=null){
                try {
                    channel1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf2!=null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(raf1!=null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //���֧�ֵ��ַ���
    @Test
    public void test5(){
        Map<String,Charset> map=Charset.availableCharsets();
        Set<Entry<String,Charset>> set=map.entrySet();

        for(Entry<String,Charset> entry:set){
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }

    //�ַ���
    @Test
    public void test6(){
        Charset cs1=Charset.forName("GBK");

        //��ȡ������
        CharsetEncoder ce=cs1.newEncoder();

        //��ȡ������
        CharsetDecoder cd=cs1.newDecoder();

        CharBuffer cBuf=CharBuffer.allocate(1024);
        cBuf.put("���������ɰ�");
        cBuf.flip();

        //����
        ByteBuffer bBuf=null;
        try {
            bBuf = ce.encode(cBuf);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }

        for(int i=0;i<12;i++){
            System.out.println(bBuf.get());//-64-78-64-78-71-2-7-2-80-55-80-55
        }

        //����
        bBuf.flip();
        CharBuffer cBuf2=null;
        try {
            cBuf2 = cd.decode(bBuf);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        System.out.println(cBuf2.toString());//���������ɰ�
    }
}

