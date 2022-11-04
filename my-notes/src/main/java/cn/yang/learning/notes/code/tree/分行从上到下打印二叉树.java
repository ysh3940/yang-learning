package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;


public class 分行从上到下打印二叉树 extends Base {

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

    private void cheng(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode cur = treeNode;
        queue.add(cur);
        TreeNode temp;
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; ++i) {
                temp = queue.poll();
                log(temp.getVal());
                if (temp.getLeft() != null) {
                    queue.add(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.add(temp.getRight());
                }
            }
            println();
        }
    }

}
