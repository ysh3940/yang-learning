package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

// 输入两棵二叉树A和B，判断B是不是A的子结构。
public class 树的子结构 extends Base {

    @Test
    public void test1() {
        TreeNode<Integer> root1 = new TreeNode<>(8);
        root1.left = new TreeNode<>(8);
        root1.right = new TreeNode<>(7);
        root1.left.left = new TreeNode<>(9);
        root1.left.right = new TreeNode<>(2);
        root1.left.right.left = new TreeNode<>(4);
        root1.left.right.right = new TreeNode<>(7);

        TreeNode<Integer> root2 = new TreeNode<>(8);
        root2.left = new TreeNode<>(9);
        root2.right = new TreeNode<>(2);

        TreeNode<Integer> root3 = new TreeNode<>(2);
        root3.left = new TreeNode<>(4);
        root3.right = new TreeNode<>(3);

        logLN("" + do1(root1, root3));
    }

    private boolean do1(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        boolean flag = false;
        if (root1.getVal() == root2.getVal()) {
            flag = do2(root1, root2);
        }
        if (flag == false) {
            flag = do1(root1.getLeft(), root2);
        }
        if (flag == false) {
            flag = do1(root1.getRight(), root2);
        }

        return flag;
    }

    private boolean do2(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 != null) {
            return false;
        } else if (root1 != null && root2 == null) {
            return true;
        } else if (root1 == null && root2 == null) {
            return true;
        } else {
            if (root1.getVal() == root2.getVal()) {
                return do2(root1.getLeft(), root2.getLeft()) && do2(root1.getRight(), root2.getRight());
            }
        }

        return false;
    }


}
