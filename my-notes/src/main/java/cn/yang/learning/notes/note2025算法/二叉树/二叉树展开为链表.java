package cn.yang.learning.notes.note2025算法.二叉树;

import java.util.ArrayList;
import java.util.List;

public class 二叉树展开为链表 {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        flatten2(root, list);

        for (int i=1; i<list.size(); ++i) {
            TreeNode prev = list.get(i-1);
            TreeNode curr = list.get(i);

            prev.right = curr;
            prev.left = null;

        }

    }

    public void flatten2(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);

        flatten2(root.left, list);
        flatten2(root.right, list);
    }


}
