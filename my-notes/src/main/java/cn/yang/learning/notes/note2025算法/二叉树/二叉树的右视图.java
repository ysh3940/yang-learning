package cn.yang.learning.notes.note2025算法.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树的右视图 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> all = new ArrayList<>();
        if (root == null) {
            return all;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (queue.isEmpty() == false) {
            Integer val2 = null;

            int size = queue.size();
            for(int i=0; i<size; ++i) {
                TreeNode node = queue.poll();
                if (i==size-1) {
                    val2 = node.val;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            all.add(val2);
        }

        return all;
    }

}
