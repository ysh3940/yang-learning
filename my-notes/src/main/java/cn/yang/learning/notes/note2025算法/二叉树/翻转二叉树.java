package cn.yang.learning.notes.note2025算法.二叉树;

public class 翻转二叉树 {

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
