package cn.yang.learning.notes.note2025.二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 二叉树的中序遍历 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal2(root, list);
        return list;
    }

    public void inorderTraversal2(TreeNode root, List<Integer> list) {
        if(root==null) {
            return;
        }
        inorderTraversal2(root.left, list);
        list.add(root.val);
        inorderTraversal2(root.right, list);
    }



    public List<Integer> inorderTraversalByStack(TreeNode root) {
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
            curr = curr.right;
        }

        return res;
    }

}
