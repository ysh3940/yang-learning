package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

import java.util.LinkedList;

// 实现两个函数，分别用来序列化和反序列化二叉树。
public class 序列化二叉树 extends 二叉树的遍历 {

    @Test
    public void test1() {
        //            1
        //          /   \
        //         2     3
        //       /      / \
        //      4      5   6
        //    1,2,4,$,$,$,3,5,$,$,6,$,$
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.left = new TreeNode<Integer>(2);
        root.right = new TreeNode<Integer>(3);
        root.left.left = new TreeNode<Integer>(4);
        root.right.left = new TreeNode<Integer>(5);
        root.right.right = new TreeNode<Integer>(6);
        logLN("前序遍历");
        qian(root);
        println();

        logLN("序列化");
        LinkedList<String> linkedList = new LinkedList<>();
        do1(root, linkedList);
        for (String s : linkedList) {
            log(s);
        }
        println();

        logLN("反序列化");
        qian(do2(linkedList));
    }

    private TreeNode do2(LinkedList<String> linkedList) {
        if (linkedList.size() == 0) {
            return null;
        }
        Object v = linkedList.poll();
        if ("$".equals(v.toString())) {
            return null;
        }
        TreeNode root = new TreeNode(v);
        root.setLeft(do2(linkedList));
        root.setRight(do2(linkedList));
        return root;
    }

    private void do1(TreeNode root, LinkedList<String> linkedList) {
        if (root == null) {
            linkedList.add("$");
            return;
        }
        linkedList.add(root.getVal() + "");
        do1(root.getLeft(), linkedList);
        do1(root.getRight(), linkedList);
    }

}
