package cn.yang.learning.notes.note2025算法.二叉树;

import java.util.ArrayList;
import java.util.List;

public class 对称二叉树 {

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return false;
        }
        return isSymmetric3(root.left, root.right);
    }

    public boolean isSymmetric3(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val && isSymmetric3(left.left, right.right)
                && isSymmetric3(left.right, right.left);
    }


    public boolean isSymmetric(TreeNode root) {
        List<Integer> res1 = new ArrayList<>();
        inorderTraversal2(root, res1, true);
        System.out.println(res1.toString());

        invertTree(root);

        List<Integer> res2 = new ArrayList<>();
        inorderTraversal2(root, res2, true);
        System.out.println(res2.toString());

        for (int i=0; i<res1.size(); ++i) {
            if (res1.get(i).intValue() != res2.get(i).intValue()) {
                return false;
            }
        }

        return true;
    }

    // 先根遍历
    public void inorderTraversal2(TreeNode root, List<Integer> list, boolean isLeft) {
        if(root==null) {
            list.add(isLeft ? -11 : -22);
            return;
        }
        list.add(root.val);
        inorderTraversal2(root.left, list, true);
        inorderTraversal2(root.right, list, false);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = right;
        root.right = left;

        invertTree(left);
        invertTree(right);

        return root;
    }


}
