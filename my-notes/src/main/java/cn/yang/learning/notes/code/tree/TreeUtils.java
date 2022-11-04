package cn.yang.learning.notes.code.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    public static boolean add(TreeNode root, TreeNode node) {
        if (node == null) {
            return false;
        }
        if (root == null) {
            root = node;
            return true;
        }
        TreeNode current = root;
        TreeNode parent;
        while (current != null) {
            parent = current;

            if (current.getValue() > node.getValue()) {
                current = current.getLeft();
                if (current == null) {
                    parent.setLeft(node);
                    return true;
                }
            } else {
                current = current.getRight();
                if (current == null) {
                    parent.setRight(node);
                    return true;
                }
            }
        }
        return false;
    }

    public void zhongErgodic(TreeNode root, List list) {
        if (root != null) {
            zhongErgodic(root.getLeft(), list);
            list.add(root.getValue());
            zhongErgodic(root.getRight(), list);
        }
    }

    public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) return null;                                        //边界条件，注意是left>right
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.setLeft(sortedArrayToBST(nums, left, mid - 1));        //递归向左探索，范围变成left~mid-1;
        root.setRight(sortedArrayToBST(nums, mid + 1, right));
        return root;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(10);
        for (int i = 1; i <= 9; ++i) {
            add(root, new TreeNode(i));
        }

        List list = new ArrayList();
        zhongErgodic(root, list);

        System.out.println(list.toString());

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode newRoot = sortedArrayToBST(array, 0, array.length - 1);
        list.clear();
        zhongErgodic(newRoot, list);
        System.out.println(list.toString());
    }

}
