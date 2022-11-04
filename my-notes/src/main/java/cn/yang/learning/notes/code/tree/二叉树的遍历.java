package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 二叉树的遍历 extends Base {

    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.right = new TreeNode<Integer>(2);
        root.right.left = new TreeNode<Integer>(3);

        logLN("前序遍历：根、左、右");
        qian(root);
        println();
        qian2(root);
        println();

        logLN("中序遍历：左、根、右");
        zhong(root);
        println();
        zhong2(root);
        println();

        logLN("后序遍历：左、右、根");
        hou(root);
        println();
        hou2(root);
        println();

        logLN("层序遍历 / 宽度优先遍历");
        cheng(root);
    }

    // 前序遍历：根、左、右
    public void qian(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        log(treeNode.getVal());
        qian(treeNode.getLeft());
        qian(treeNode.getRight());
    }

    // 非递归
    private void qian2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack();
        stack.push(treeNode);
        TreeNode temp;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            log(temp.getVal());
            if (temp.getRight() != null) {
                stack.push(temp.getRight());
            }
            if (temp.getLeft() != null) {
                stack.push(temp.getLeft());
            }
        }
    }

    // 中序遍历：左、根、右
    public void zhong(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        zhong(treeNode.getLeft());
        log(treeNode.getVal());
        zhong(treeNode.getRight());
    }

    private void zhong2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode cur = treeNode;
        TreeNode temp;
        Stack<TreeNode> stack = new Stack();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            } else {
                temp = stack.pop();
                log(temp.getVal());
                cur = temp.getRight();
            }
        }
    }

    // 后序遍历：左、右、根
    public void hou(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        hou(treeNode.getLeft());
        hou(treeNode.getRight());
        log(treeNode.getVal());
    }

    private void hou2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode cur = treeNode;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            } else {
                cur = stack.peek().getRight();
                if (cur != null && cur != pre) {
                    stack.push(cur);
                    cur = cur.getLeft();
                } else {
                    pre = stack.pop();
                    log(pre.getVal());
                    cur = null;
                }
            }
        }
    }

    // 层序遍历 / 宽度优先遍历 用queue队列实现
    public void cheng(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode cur = treeNode;
        queue.add(cur);
        TreeNode temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            log(temp.getVal());
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
        }
    }

}
