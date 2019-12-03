package com.server.DataStructure.Tree;

import com.server.DataStructure.TreeNode;

/**
 * 二叉查找树：对于树中的任意一个节点，
 * 左子树中的每个节点的值，都小于这个节点的值，
 * 右子树节的每个节点的值，都大于这个节点的值。
 *
 * 注意：该类讨论的二叉查找树 不支持  重复数据
 */
public class BinarySearchTree extends TraverseTree{

    public BinarySearchTree(){}

    public BinarySearchTree(TreeNode root) {
        super(root);
    }

    // 查找
    public TreeNode search(int target){
        TreeNode p = root;
        while(p!=null){
            if(p.data==target) return p;
            if(p.data>target) p=p.leftNode;
            if(p.data<target) p=p.rightNode;
        }
        return null;
    }

    //插入  该方法不讨论插入 重复值
    public void insert(int target){
        if(root == null){
            root = new TreeNode(target);
            return;
        }
        TreeNode p = root;
        TreeNode tmp = null;
        while(p!=null){
            if(p.data>target){
                if(p.leftNode==null){
                    tmp= new TreeNode(target);
                    p.leftNode=tmp;
                    return;
                }
                p=p.leftNode;
            }
            if(p.data<target){
                if(p.rightNode==null){
                    tmp = new TreeNode(target);
                    p.rightNode=tmp;
                    return;
                }
                p=p.rightNode;
            }
        }
    }

    //删除
    public void delete(int target){
        // 查找要删除的点，并记录 父节点
        TreeNode p = root;
        TreeNode parent = null;
        while(p!=null&&p.data!=target){
            parent=p;
            if(p.data>target) p=p.rightNode;
            else p=p.leftNode;
        }
        //没有找到要删除的节点就退出
        if(p==null) return;

        //遍历结束后，p节点就是 要删除的节点

        //要删除的节点，有两个子节点，则需要找到右子树中的最小节点，然后替换要删除的节点
        if(p.leftNode!=null&&p.rightNode!=null){
            TreeNode rightTreeNode = p.rightNode;
            TreeNode rightTreeNodeParent = rightTreeNode;
            //找 右子树中的 最小 节点
            while (rightTreeNode.leftNode!=null){
                rightTreeNodeParent=rightTreeNode;
                rightTreeNode=rightTreeNode.leftNode;
            }
            // 遍历结束后 rightTreeNode 就是 右子树最小节点，rightTreeNodeParent 是最小节点的父节点
            p.data= rightTreeNode.data;
            p = rightTreeNode;
            parent= rightTreeNodeParent;
        }

        //删除的节点是叶子节点或者只有一个子节点
        TreeNode child ;
        if(p.leftNode!=null) child=p.leftNode;
        else if(p.rightNode!=null) child=p.rightNode;
        else child=null;

        if (parent == null) root = child; // 删除的是根节点
        else if (parent.leftNode == p) parent.leftNode = child;
        else parent.rightNode = child;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        BinarySearchTree binarySearchTree = new BinarySearchTree(root);
        binarySearchTree.insert(2);
        binarySearchTree.MidOrder(root);
    }


}
