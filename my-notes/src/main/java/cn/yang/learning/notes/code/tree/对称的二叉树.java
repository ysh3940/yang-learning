package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 对称的二叉树 extends Base {

    @Test
    public void test2() {
        TreeNode<Integer> root = new TreeNode<>(8);
        root.left = new TreeNode<>(6);
        root.right = new TreeNode<>(6);
        root.left.left = new TreeNode<>(5);
        root.left.right = new TreeNode<>(7);
        root.right.left = new TreeNode<>(7);
        root.right.right = new TreeNode<>(5);

        boolean res = do1(root, root);
        logLN(res + "");
    }

    private boolean do1(TreeNode root, TreeNode rootFan) {
        if (root == null && rootFan == null) {
            return true;
        }
        if (root == null || rootFan == null) {
            return false;
        }
        if (root.getVal() != rootFan.getVal()) {
            return false;
        }
        return do1(root.getLeft(), rootFan.getRight()) && do1(root.getRight(), rootFan.getLeft());
    }


    @Test
    public void test1() {
        TreeNode<Integer> root = new TreeNode<>(8);
        root.left = new TreeNode<>(6);
        root.right = new TreeNode<>(6);
        root.left.left = new TreeNode<>(5);
        root.left.right = new TreeNode<>(7);
        root.right.left = new TreeNode<>(7);
        root.right.right = new TreeNode<>(5);

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        qian(root, list1);
        qianFan(root, list2);

        for (String s : list1) {
            log(s);
        }
        println();
        for (String s : list2) {
            log(s);
        }
        println();


        boolean flag = true;
        for (int i = 0; i < list1.size(); ++i) {
            if (list1.get(i).equalsIgnoreCase(list2.get(i)) == false) {
                flag = false;
                break;
            }
        }
        logLN("" + flag);
    }

    // 前序遍历：根、左、右
    public void qian(TreeNode treeNode, List<String> list) {
        if (treeNode == null) {
            list.add("null");
            return;
        }
        list.add(treeNode.getVal() + "");
        qian(treeNode.getLeft(), list);
        qian(treeNode.getRight(), list);
    }

    // 前序遍历（先右后左)：根、右、左
    public void qianFan(TreeNode treeNode, List<String> list) {
        if (treeNode == null) {
            list.add("null");
            return;
        }
        list.add(treeNode.getVal() + "");
        qianFan(treeNode.getRight(), list);
        qianFan(treeNode.getLeft(), list);
    }


}
