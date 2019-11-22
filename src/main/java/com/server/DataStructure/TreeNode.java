package com.server.DataStructure;

public class TreeNode {

    public int data;

    public TreeNode leftNode;

    public TreeNode rightNode;

    public TreeNode(int data) {
        this.data = data;
    }

    public TreeNode(int data, TreeNode leftNode, TreeNode rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }
}
