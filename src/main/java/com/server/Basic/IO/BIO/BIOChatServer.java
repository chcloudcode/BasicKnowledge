package com.server.Basic.IO.BIO;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
public class BIOChatServer {

    private static final int DEFAULT_PORT = 6789;

    private static final String DEFAULT_LOCATION="127.0.0.1";

    //客户端连接(socket连接)的队列最大长度
    public static final int DEFAULT_BACKLOG = 50;

    private static final int MAX_CLIENT_CONNECT = 10;

    private final Executor threadPool = Executors.newFixedThreadPool(10);

    //服务端口
    private final int port;

    //连接队列的最大长度
    private final int backlog;

    //地址
    private final String location;

    //Socket监听服务对象
    private ServerSocket serverSocket;

    //Socket服务监听地址包装类
    private InetAddress inetAddress;

    //这是一个线程安全的非阻塞的队列
    private final ConcurrentLinkedQueue<Socket> clientSockets = new ConcurrentLinkedQueue<>();

    /**
     * 创建一个默认的聊天室服务端
     *
     * @return
     * @throws IOException
     */
    public static BIOChatServer createServerByDefault() throws IOException {
        return new BIOChatServer(DEFAULT_PORT, DEFAULT_BACKLOG, DEFAULT_LOCATION);
    }

    /**
     * 创建一个自定义端口的服务端
     *
     * @param port
     * @return
     * @throws IOException
     */
    public static BIOChatServer newServerByPort(int port) throws IOException {
        return new BIOChatServer(port, DEFAULT_BACKLOG, DEFAULT_LOCATION);
    }

    /**
     * 创建一个自定义端口和连接队列深度的服务
     *
     * @param port    端口
     * @param logback 连接队列最大num
     * @return
     * @throws IOException
     */
    public static BIOChatServer newServerWithDefaultLocation(int port, int logback) throws IOException {
        return new BIOChatServer(port, logback, DEFAULT_LOCATION);
    }

    /**
     * 自定义端口、连接大小、地址的服务端对象
     *
     * @param port
     * @param logback
     * @param location
     * @return
     * @throws IOException
     */
    public static BIOChatServer newServerDynamic(int port, int logback, String location) throws IOException {
        return new BIOChatServer(port, logback, location);
    }

    /**
     * @param port     监听的端口号
     * @param backlog  客户端连接的队列最大长度
     * @param location 地址
     * @throws IOException
     */
    private BIOChatServer(int port, int backlog, String location) throws IOException {
        this.port = port;
        this.location = location;
        this.backlog = backlog;
        inetAddress = InetAddress.getByName(location);
        //创建一个端口监听对象，监听客户端连接，此刻服务端已经就绪
        serverSocket = new ServerSocket(port, backlog, inetAddress);
        log.info("BIO server has started in " + location + " listening port " + port);
    }
    /**
     * accept 监听客户端的连接
     * 1. accept()获取客户端Socket
     * 2. provideService（）提供处理客户端消息的服务
     *
     * @throws IOException
     */
    public void listen() throws IOException {
        while (true) {
            Socket client = serverSocket.accept();
            clientSockets.add(client);
            log.info(client.getInetAddress().getHostAddress() + "加入了聊天室");
            log.info("当前在线人数：" + clientSockets.size());
            threadPool.execute(() -> provideService(client));
        }
    }

    /**
     * 提供服务 轮询去客户端处理消息
     *
     * @param client
     */
    public void provideService(Socket client) {
        while (true) {
            //自定义协议 消息对象
            Message message = null;
            try {
                //1. 获取客户端发送至服务端的消息
                message = readRequest(client);
                //1.如果客户端队列的size为0，就说明所有客户端都已经下线，直接结束轮询
                if (clientSockets.size() == 0) {
                    break;
                } else {
                    //转发消息至所有客户端
                    dispatch(message);
                }
            } catch (IOException e) {
                //这里的异常表示客户端已经下线，此时需要去清除客户端连接资源
                log.warn(e.getMessage());
                remove(client);
                break;
            }
        }
    }

    /**
     * 读取客户端发送来的消息
     *
     * @param client 客户端Socket
     * @return
     * @throws IOException
     */
    public Message readRequest(Socket client) throws IOException {
        InputStream in = client.getInputStream();
        //自定义协议负责去解析消息
        return BioChartRoomProtocol.parse(in);
    }

    /**
     * 转发请求至所有客户端
     *
     * @param message
     */
    public void dispatch(Message message) {
        Iterator<Socket> clients = clientSockets.iterator();
        Socket client = null;
        //遍历发送至客户端，该方法是同步方法，so，客户端收到的消息是先后顺序和其进入聊天室的顺序是一致的
        while (clients.hasNext()) {
            try {
                client = clients.next();
                log.info( "转发消息至" + message.getHeader().toString());
                //协议去写入消息至客户端
                BioChartRoomProtocol.write(client.getOutputStream(), message);
            } catch (IOException e) {
                //这里的异常表示客户端已经下线，此时需要去清除客户端连接资源
                e.printStackTrace();
                log.info(e.getMessage());
                remove(client);
            }
        }
    }

    /**
     * 清除资源 从队列中去移除
     *
     * @param client
     */
    public void remove(Socket client) {
        try {
            boolean result = clientSockets.remove(client);
            if (result && client != null && !client.isClosed()) {
                //关闭连接
                client.close();
                log.warn( "移除client" + client.getInetAddress().getHostAddress() + ":" + client.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.warn(e.getMessage());
        }
    }

    public static void main(String[] args) {
       try {
           BIOChatServer bioServer = BIOChatServer.newServerByPort(9090);
           bioServer.listen();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

}

