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



    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        BinarySearchTree binarySearchTree = new BinarySearchTree(root);
        binarySearchTree.insert(2);
        binarySearchTree.MidOrder(root);
    }


}
