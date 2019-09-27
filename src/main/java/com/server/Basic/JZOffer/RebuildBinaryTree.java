package com.server.Basic.JZOffer;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class RebuildBinaryTree {

	public static void main(String[] args) {
		int[] pre = new int[] { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] in = new int[] { 4, 7, 2, 1, 5, 3, 8, 6 };
		TreeNode root = reConstructBinaryTree(pre, in);
	}

	public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
		return reConstructBinaryTree(pre, in, 0, pre.length - 1, 0, in.length - 1);
	}

	private static TreeNode reConstructBinaryTree(int[] pre, int[] in, int startPre, int endPre, int startIn,
			int endIn) {
		if (startPre > endPre || startIn > endIn) {
			return null;
		}
		TreeNode root = new TreeNode(pre[startPre]);

		for (int i = startIn; i <= endIn; i++) {
			if (in[i] == pre[startPre]) {
				root.left = reConstructBinaryTree(pre, in, startPre + 1, startPre + i - startIn, startIn, i - 1);
				root.right = reConstructBinaryTree(pre, in, i - startIn + startPre + 1, endPre, i + 1, endIn);
			}
		}
		return root;
	}

}
