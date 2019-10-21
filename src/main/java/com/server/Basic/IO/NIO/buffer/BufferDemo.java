package com.server.Basic.IO.NIO.buffer;


import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 编码、解码
 */
public class BufferDemo {

    /**
     * 解码   字符-> 对应编码字节 -> 字符
     * @param str
     * @throws UnsupportedEncodingException
     */
    public static void decode(String str) throws UnsupportedEncodingException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put(str.getBytes("UTF-8"));
        //获取数据，需要重置 position和limit指针的位置，所以需要调用flip方法，其余指针：mark capacity
        byteBuffer.flip();

        //获取utf-8的编码器
        Charset utf8 = Charset.forName("UTF-8");
        //对byteBuffer中的内容解码
        CharBuffer charBuffer = utf8.decode(byteBuffer);

        //charBuffer.array() 就是内部的数组引用
        char[] chararr = Arrays.copyOf(charBuffer.array(),charBuffer.limit());
        System.out.println(chararr);
    }

    /**
     * 编码 字符 -> 字节
     * @param str
     */
    public static void encode(String str){
        CharBuffer charBuffer = CharBuffer.allocate(128);
        charBuffer.append(str);
        charBuffer.flip();

        //获取utf-8的编码器
        Charset utf8 = Charset.forName("UTF-8");
        //对byteBuffer中的内容解码
        ByteBuffer byteBuffer = utf8.encode(charBuffer);

        //byteBuffer.array() 就是内部的数组引用
        byte[] bytearr = Arrays.copyOf(byteBuffer.array(),byteBuffer.limit());
        System.out.println(Arrays.toString(bytearr));

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        BufferDemo.decode("我爱你中国");
        BufferDemo.encode("亲爱的母亲");
    }


}
