package cn.yang.learning.notes.note2025.二叉树;

public class 验证二叉搜索树 {

    public boolean isValidBST(TreeNode root) {
        return isValidBST2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST2(TreeNode root, Long min, Long max) {
        if (root == null) {
            return true;
        }

        boolean res = true;
        if (root.val <= min || root.val >= max) {
            res = false;
        }

        boolean left = isValidBST2(root.left, min, Long.valueOf(root.val));
        boolean right = isValidBST2(root.right, Long.valueOf(root.val), max);

        return left && right && res;
    }


}
