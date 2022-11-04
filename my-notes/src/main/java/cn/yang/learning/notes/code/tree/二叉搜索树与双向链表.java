package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

// 题目要求：
// 输入一颗二叉搜索树，将该二叉搜索树转换成一个排序的双向链表，不能创建任何新的节点，
// 只能调整树中节点指针的指向。
public class 二叉搜索树与双向链表 extends Base {

    @Test
    public void test1() {
        //            10
        //          /   \
        //         6     14
        //       /  \   / \
        //      4    8 12  16
        TreeNode<Integer> root = new TreeNode<>(10);
        root.left = new TreeNode<Integer>(6);
        root.right = new TreeNode<Integer>(14);
        root.left.left = new TreeNode<Integer>(4);
        root.left.right = new TreeNode<Integer>(8);
        root.right.left = new TreeNode<Integer>(12);
        root.right.right = new TreeNode<Integer>(16);

        TreeNode first = zhong(root, null);
        while (true) {
            if (first != null) {
                log(first.getVal());
                first = first.getRight();
            } else {
                break;
            }
        }
    }


    // 中序遍历：左、根、右
    public TreeNode zhong(TreeNode treeNode, TreeNode father) {
        if (treeNode == null) {
            return null;
        }
        TreeNode qian = zhong(treeNode.getLeft(), treeNode);
        treeNode.setLeft(qian);
        if (qian != null) {
            qian.setRight(treeNode);
        } else {
            qian = treeNode;
        }

        TreeNode hou = zhong(treeNode.getRight(), treeNode);
        treeNode.setRight(hou);
        if (hou != null) {
            hou.setLeft(treeNode);
        } else {
            hou = treeNode;
        }

        if (father == null) {
            TreeNode pre = null;
            TreeNode cur = treeNode;
            while (cur != null) {
                pre = cur;
                cur = cur.getLeft();
            }
            return pre;
        }
        if (treeNode == father.getLeft()) {
            return hou;
        } else {
            return qian;
        }
    }

}
