package com.server.Basic.IO.BIO.ChatDemo;

import java.io.*;

public class BioChartRoomProtocol {

    /**
     * 些消息 适用于客户单发消息
     * @param outputStream
     * @param header
     * @param content
     * @throws IOException
     */
    public static void write(OutputStream outputStream, Header header, String content) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        Message message = new Message(header, content);
        objectOutputStream.writeObject(message);
    }

    /***
     * 写消息 使用服务端转发消息
     * @param outputStream
     * @param message
     * @throws IOException
     */
    public static void write(OutputStream outputStream, Message message) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(message);
    }

    /**
     * 读操作，将消息转化成消息对象
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static Message parse(InputStream inputStream) throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Message message = null;
        try {
            message = (Message) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new Error(e.getMessage());
        }
        return message;
    }

}
