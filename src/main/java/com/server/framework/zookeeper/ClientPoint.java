package com.server.framework.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户端 连接zk  监听服务器端node
 */
public class ClientPoint {

    private String connectString = "192.168.111.130:2181,192.168.111.131:2181,192.168.111.132:2181";
    private static int sessionTimeout = 2000;
    private ZooKeeper zkClient = null;
    private String parentNode = "/servers";

    private ArrayList<String> serversList = new ArrayList<>();

    /**
     * 获取连接zkServer的客户端
     * @throws Exception
     */
    public void getConnect() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取子节点并对父节点进行监听
     * @throws Exception
     */
    public void getServerList() throws Exception {
        // 获取服务器子节点信息，并且对父节点进行监听
        List<String> children = zkClient.getChildren(parentNode, true);

        ArrayList<String> servers = new ArrayList<>();
        for (String child : children) {
            byte[] data = zkClient.getData(parentNode + "/" + child, false, null);
            servers.add(new String(data));
            // 把 servers 赋值给成员 serverList ，已提供给各业务线程使用
            serversList = servers;
            System.out.println(serversList);
        }
    }

    /**
     * 业务代码
     * @throws Exception
     */
    public void business() throws Exception{
        System.out.println("client is working ....");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception{
        ClientPoint clientPoint = new ClientPoint();
        clientPoint.getConnect();
        clientPoint.getServerList();
        clientPoint.business();
    }


}
