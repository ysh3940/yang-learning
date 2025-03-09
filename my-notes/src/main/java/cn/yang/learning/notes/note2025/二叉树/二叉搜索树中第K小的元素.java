package cn.yang.learning.notes.note2025.二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 二叉搜索树中第K小的元素 {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = inorderTraversalByStack(root, k);
        return list.get(k-1);
    }

    public List<Integer> inorderTraversalByStack(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while (curr != null || stack.isEmpty() == false) {
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            res.add(curr.val);
            if (res.size() >= k) {
                break;
            }
            curr = curr.right;
        }

        return res;
    }

}
