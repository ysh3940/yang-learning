package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

public class 二叉树的深度 extends Base {

    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.left.right.left = new TreeNode<>(7);
        root.right = new TreeNode<>(3);
        root.right.right = new TreeNode<>(6);

        logLN("" + do1(root));
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
