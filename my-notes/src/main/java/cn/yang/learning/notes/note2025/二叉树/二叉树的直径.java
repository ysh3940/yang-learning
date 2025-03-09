package cn.yang.learning.notes.note2025.二叉树;

public class 二叉树的直径 {

    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTree2(root);
        return max;
    }

    public int diameterOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);

        max = Math.max(max, left+right);

        // 返回的是节点的深度
        return Math.max(left, right) + 1;
    }



}
