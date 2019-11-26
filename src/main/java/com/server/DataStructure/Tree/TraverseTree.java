package com.server.DataStructure.Tree;

import com.server.DataStructure.TreeNode;

public class TraverseTree {

    TreeNode root;

    public TraverseTree(){

    }

    public TraverseTree(TreeNode root){
        this.root=root;
    }

    public  void PreOrder(TreeNode node){
        if(node == null) return ;
        System.out.print(node.data+"  ");
        PreOrder(node.leftNode);
        PreOrder(node.rightNode);
    }

    public void MidOrder(TreeNode node){
        if(node == null) return ;
        MidOrder(node.leftNode);
        System.out.print(node.data+"  ");
        MidOrder(node.rightNode);
    }

    public void PostOrder(TreeNode node){
        if(node == null) return ;
        PostOrder(node.leftNode);
        PostOrder(node.rightNode);
        System.out.print(node.data+"  ");
    }
}
