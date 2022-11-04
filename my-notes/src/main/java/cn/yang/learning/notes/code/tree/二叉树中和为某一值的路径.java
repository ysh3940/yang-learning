package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

// 题目要求：
// 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
// 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
public class 二叉树中和为某一值的路径 extends Base {

    @Test
    public void test1() {
        //            10
        //          /   \
        //         5     12
        //       /  \
        //      4    7
        TreeNode<Integer> root = new TreeNode<>(10);
        root.left = new TreeNode<Integer>(5);
        root.right = new TreeNode<Integer>(12);
        root.left.left = new TreeNode<Integer>(4);
        root.left.right = new TreeNode<Integer>(7);

        int sum = 22;
        do1(root, new ArrayList<>(), sum);
    }

    private void do1(TreeNode<Integer> root, List<Integer> path, int sum) {
        if (root == null) {
            return;
        }
        path.add(root.getVal());
        if (root.getLeft() == null && root.getRight() == null) {
            Integer temp = 0;
            for (Integer i : path) {
                temp += i;
            }
            if (temp == sum) {
                for (Integer i : path) {
                    log(i);
                }
                println();
            }
        }
        if (root.getLeft() != null) {
            do1(root.getLeft(), path, sum);
        }
        if (root.getRight() != null) {
            do1(root.getRight(), path, sum);
        }
        path.remove(root.getVal());
    }


}
