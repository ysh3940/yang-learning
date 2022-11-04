package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 二叉搜索树的第k大节点 extends Base {

    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(5);
        root.left = new TreeNode<>(3);
        root.left.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(4);
        root.right = new TreeNode<>(7);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(8);
        zhong(root, new ArrayList(), 3);//4
        // zhong(root,6);//7
        // zhong(root,8);//null
    }

    public void zhong(TreeNode treeNode, List list, int k) {
        if (treeNode == null) {
            return;
        }
        zhong(treeNode.getLeft(), list, k);
        list.add("1");
        if (list.size() == k) {
            log(treeNode.getVal());
            return;
        }
        zhong(treeNode.getRight(), list, k);
    }

}
