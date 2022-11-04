package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

// 题目要求：
// 求一棵二叉树的镜像。
// 解题思路：
// 二叉树的镜像，即左右子树调换。从上到下，递归完成即可。
public class 二叉树的镜像 extends 二叉树的遍历 {

    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(8);
        root.left = new TreeNode<>(6);
        root.right = new TreeNode<>(10);
        root.left.left = new TreeNode<>(5);
        root.left.right = new TreeNode<>(7);
        root.right.left = new TreeNode<>(9);
        root.right.right = new TreeNode<>(11);
        cheng(root);
        do1(root);
        println();
        cheng(root);
    }

    private void do1(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.getLeft();
        root.setLeft(root.getRight());
        root.setRight(temp);
        if (root.getLeft() != null) {
            do1(root.getLeft());
        }
        if (root.getRight() != null) {
            do1(root.getRight());
        }
    }


}
