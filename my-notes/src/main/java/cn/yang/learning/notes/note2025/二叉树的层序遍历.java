package cn.yang.learning.notes.note2025;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 二叉树的层序遍历 {

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
        System.out.println(root.val);
        list.add(root);


        flatten2(root.left, list);
        flatten2(root.right, list);

    }





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




    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        kthSmallest2(root, list, k);
        return list.get(k-1);
    }

    public void kthSmallest2(TreeNode root, List<Integer> list, int k) {
        if (root == null) {
            return;
        }

        kthSmallest2(root.left, list, k);

        list.add(root.val);

        //System.out.println(list);
        kthSmallest2(root.right, list, k);

    }




    public boolean isValidBST(TreeNode root) {
        if (root==null) {
            return false;
        }

        return isValidBST2(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    public boolean isValidBST2(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        boolean isTrue = true;
        if (root.val <= min || root.val >= max) {
            isTrue = false;
        }

        boolean left3 = isValidBST2(root.left, min, root.val);
        boolean right3 = isValidBST2(root.right, root.val, max);


        return isTrue && left3 && right3;
    }





    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> all = new ArrayList<>();
        if (root == null) {
            return all;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (queue.isEmpty() == false) {
            List<Integer> one = new ArrayList<>();
            int size = queue.size();
            for(int i=0; i<size; ++i) {
                TreeNode node = queue.poll();
                one.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            all.add(one);
        }

        return all;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}
