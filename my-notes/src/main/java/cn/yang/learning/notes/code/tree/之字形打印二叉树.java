package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

// 题目要求：
// 请实现一个函数按照之字形打印二叉树。
// 即第一层从左到右打印，第二层从右到左打印，第三层继续从左到右，以此类推。
public class 之字形打印二叉树 extends Base {

    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(8);
        root.left = new TreeNode<>(6);
        root.right = new TreeNode<>(7);
        root.left.left = new TreeNode<>(5);
        root.left.right = new TreeNode<>(7);
        root.right.left = new TreeNode<>(7);
        root.right.right = new TreeNode<>(5);
        //            8
        //          /   \
        //         6     7
        //       /  \   / \
        //      5    7 7   5
        // 按题目打印出来应该是
        // 8 -->
        // 7 6 <--
        // 5 7 7 5 -->

        LinkedList<TreeNode> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        int size;
        TreeNode temp;
        while (!queue.isEmpty() || !stack.isEmpty()) {
            if (!queue.isEmpty()) {
                size = queue.size();
                for (int i = 0; i < size; ++i) {
                    temp = queue.poll();
                    log(temp.getVal());
                    if (temp.getRight() != null) {
                        stack.push(temp.getRight());
                    }
                    if (temp.getLeft() != null) {
                        stack.push(temp.getLeft());
                    }
                }
            } else {
                size = stack.size();
                for (int i = 0; i < size; ++i) {
                    temp = stack.pop();
                    log(temp.getVal());
                    if (temp.getRight() != null) {
                        queue.add(temp.getRight());
                    }
                    if (temp.getLeft() != null) {
                        queue.add(temp.getLeft());
                    }
                }
            }
            println();
        }
    }

}
