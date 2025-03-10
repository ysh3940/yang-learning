package cn.yang.learning.notes.note2025.二叉树;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的最近公共祖先 {

    public  TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pList = new ArrayList<>();
        List<TreeNode> qList = new ArrayList<>();


        TreeNode n1 = lowestCommonAncestor2(root, p, pList);
        if (n1 != null) {
            pList.add(n1);
        }

        TreeNode n2 = lowestCommonAncestor2(root, q, qList);
        if (n2 != null) {
            qList.add(n2);
        }

        TreeNode result = null;

        int pSize = pList.size();
        int qSize = qList.size();
        for (int i=0; i<Math.min(pSize, qSize); ++i) {
            TreeNode p1 = pList.get(pSize-1-i);
            TreeNode q1 = qList.get(qSize-1-i);
            if (p1 == q1) {
                result = qList.get(qSize-1-i);
            }
        }

        return result;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, List<TreeNode> list) {
        if (root==null) {
            return null;
        }

        if (p == root) {
            return root;
        }

        TreeNode t1 =  lowestCommonAncestor2(root.left, p, list);
        if (t1 != null) {
            list.add(t1);
            return root;
        }
        TreeNode t2 = lowestCommonAncestor2(root.right, p, list);
        if (t2 != null) {
            list.add(t2);
            return root;
        }

        return null;
    }

}
