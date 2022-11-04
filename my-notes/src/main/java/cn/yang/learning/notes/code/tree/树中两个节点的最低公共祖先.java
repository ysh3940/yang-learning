package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

// 题目要求：
// 输入一棵树的根节点，输入两个被观察节点，求这两个节点的最低(最近)公共祖先。
public class 树中两个节点的最低公共祖先 extends Base {

    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(8);
        root.left = new TreeNode<>(6);
        root.right = new TreeNode<>(7);
        root.left.left = new TreeNode<>(1);
        root.left.right = new TreeNode<>(2);
        root.right.left = new TreeNode<>(3);
        root.right.right = new TreeNode<>(4);
        //            8
        //          /   \
        //         6     7
        //       /  \   / \
        //      1    3 3   4
        logLN(do1(root, root.left.left, root.right.right).getVal());
    }

    private TreeNode do1(TreeNode node, TreeNode node1, TreeNode node2) {
        if (node == null) {
            return null;
        }
        if (node.getVal() == node1.getVal() || node.getVal() == node2.getVal()) {
            return node;
        }
        TreeNode left = do1(node.getLeft(), node1, node2);
        TreeNode right = do1(node.getRight(), node1, node2);
        if (left == null && right == null) {
            return null;
        }
        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right;
    }


}
