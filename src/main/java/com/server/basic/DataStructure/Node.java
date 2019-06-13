
package com.server.basic.DataStructure;

/**
 * 单链表
 */
public class Node {

    public int data;

    public Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
