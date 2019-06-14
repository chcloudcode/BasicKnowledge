package com.server.framework.Zookeeper;


import org.apache.zookeeper.*;

/**
 * 服务器端 连接zk  创建node 上线
 */
public class ServerPoint {


    private String connectString = "192.168.111.130:2181,192.168.111.131:2181,192.168.111.132:2181";
    private static int sessionTimeout = 2000;
    private ZooKeeper zkClient = null;
    private String parentNode = "/servers";

    /**
     * 获取连接zkServer的客户端
     * @throws Exception
     */
    public void getConnect() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {


            }
        });
    }

    /**
     * 当连接上zkServer时 创建一个zknode 表示 服务器上线了
     * @param hostname
     * @throws Exception
     */
    public void registServer(String hostname) throws Exception {
        String create = zkClient.create(parentNode+"/server",hostname.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(hostname+"is online"+create);
    }

    /**
     * 业务逻辑
     * @param hostname
     * @throws Exception
     */
    public void business(String hostname) throws Exception {
        System.out.println(hostname+"is working .....");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        ServerPoint s = new ServerPoint();
        s.getConnect();
        String hostname = "server"+Math.random()*10;
        s.registServer(hostname);
        s.business(hostname);
    }


}
