package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

public class 从上到下打印二叉树 extends 二叉树的遍历 {

    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(8);
        root.left = new TreeNode<>(6);
        root.right = new TreeNode<>(6);
        root.left.left = new TreeNode<>(5);
        root.left.right = new TreeNode<>(7);
        root.right.left = new TreeNode<>(7);
        root.right.right = new TreeNode<>(5);

        cheng(root);
    }


}
