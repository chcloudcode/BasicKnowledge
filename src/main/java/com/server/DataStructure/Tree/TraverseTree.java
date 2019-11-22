package com.server.DataStructure.Tree;

import com.server.DataStructure.TreeNode;

public class TraverseTree {

    public static  void PreOrder(TreeNode node){
        if(node == null) return ;
        System.out.print(node.data+"");
        PreOrder(node.leftNode);
        PreOrder(node.rightNode);
    }

    public static void MidOrder(TreeNode node){
        if(node == null) return ;
        MidOrder(node.leftNode);
        System.out.print(node.data+"");
        MidOrder(node.rightNode);
    }

    public static void PostOrder(TreeNode node){
        if(node == null) return ;
        PostOrder(node.leftNode);
        PostOrder(node.rightNode);
        System.out.print(node.data+"");
    }

    public static void main(String[] args) {
        TreeNode node1= new TreeNode(1);
        TreeNode node2= new TreeNode(2);
        TreeNode node3= new TreeNode(3);
        TreeNode node4= new TreeNode(4);
        TreeNode node5= new TreeNode(5);
        TreeNode node6= new TreeNode(6);
        TreeNode node7= new TreeNode(7);
        TreeNode node8= new TreeNode(8);
        node1.leftNode=node2;
        node1.rightNode=node3;
        node2.leftNode=node4;
        node2.rightNode=node5;
        node3.leftNode=node6;
        node3.rightNode=node7;
        node4.leftNode=node8;

        TraverseTree.PreOrder(node1);
        System.out.println();
        TraverseTree.MidOrder(node1);
        System.out.println();
        TraverseTree.PostOrder(node1);
    }
}
