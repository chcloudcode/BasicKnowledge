package com.server.Basic.IO.NIO.channel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChannelDemo {

    public static void main(String[] args) throws IOException {

        try {
            File file = new File("/Users/chenhui/Documents/hello.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            //根据文件输出流创建 文件通道
            FileOutputStream fos = new FileOutputStream(file);
            FileChannel fileChannel = fos.getChannel();

            //向byteBuffer 写入字符串
            ByteBuffer bb = ByteBuffer.allocate(64);
            bb.put("呆，贼子休走！".getBytes("UTF-8"));
            bb.flip();

            //将ByteBuffer中的元素写入打通道
            fileChannel.write(bb);
            bb.clear();

            bb.put("汝，欺人太甚".getBytes("UTF-8"));
            bb.flip();
            fileChannel.write(bb);
            bb.clear();

            fos.close();
            fileChannel.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        //读数据
        Path path = Paths.get("/Users/chenhui/Documents/hello.txt");
        FileChannel fc = FileChannel.open(path);
        ByteBuffer bb = ByteBuffer.allocate((int) (fc.size()+1));
        Charset utf8 = Charset.forName("UTF-8");

        //阻塞模式，读取完才能返回
        fc.read(bb);
        bb.flip();
        CharBuffer cb = utf8.decode(bb);
        System.out.println(cb.toString());
        bb.clear();
        fc.close();

    }
}
