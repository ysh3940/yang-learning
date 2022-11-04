package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.Count;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

// 题目要求：
// 输入二叉树的根节点，判断该树是否是平衡二叉树。如果某二叉树的任意节点的左右子树深度之差不超过1，则该树是平衡二叉树。
public class 平衡二叉树 extends Base {

    @Test
    public void test2() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.left.right.left = new TreeNode<>(7);
        root.right = new TreeNode<>(3);
        root.right.right = new TreeNode<>(6);

        logLN("" + do3(root, new Count()));
    }

    // 利用后续遍历的思路解决
    private boolean do3(TreeNode node, Count count) {
        if (node == null) {
            count.count = 0;
            return true;
        }
        Count left = new Count();
        Count right = new Count();
        if (do3(node.getLeft(), left) && do3(node.getRight(), right)) {
            if (Math.abs(left.count - right.count) <= 1) {
                count.count = 1 + Math.max(left.count, right.count);
                return true;
            }
        }

        return false;
    }

    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.left.right.left = new TreeNode<>(7);
        root.right = new TreeNode<>(3);
        root.right.right = new TreeNode<>(6);

        logLN("" + do2(root));
    }

    private boolean do2(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        int l = do1(root.getLeft());
        int r = do1(root.getRight());

        if (Math.abs(l - r) > 1) {
            return false;
        }

        return do2(root.getLeft()) && do2(root.getRight());
    }

    private int do1(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        }
        int left = do1(root.left);
        int right = do1(root.getRight());
        return left > right ? left + 1 : right + 1;
    }


}
