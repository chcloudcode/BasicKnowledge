package com.server.LeetCode.simple;

import com.server.LeetCode.TreeNode;

/**
 * 判断给定的 二叉树 是否相同
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的
 */
public class JudgeSameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if(p == null && q == null ) return true;
        if((p == null && q!= null)|| (p !=null && q==null)) return false;

        if(p.val!=q.val) return false;
        boolean lresult = isSameTree(p.left,q.left);
        boolean rresult = isSameTree(p.right,q.right);
        if(lresult && rresult) return true;
        else return false;
    }

    public static void main(String[] args) {
        TreeNode t1= new TreeNode(1);
        TreeNode t2= new TreeNode(2);
        TreeNode t3= new TreeNode(1);
        TreeNode t4= new TreeNode(1);
        TreeNode t5= new TreeNode(1);
        TreeNode t6= new TreeNode(2);
        t1.left=t2;
        t1.right=t3;
        t4.left=t5;
        t4.right=t6;

        JudgeSameTree j = new JudgeSameTree();
        System.out.println(j.isSameTree(t1,t4));
    }

}
