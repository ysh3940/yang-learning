package cn.yang.learning.notes.code.tree;

import java.util.List;

/**
 * 二叉树（左节点 < 根节点 < 右节点）
 */
public class BinaryTree {
    // 根节点
    private TreeNode root;

    /**
     * 添加节点
     *
     * @param node
     * @return
     */
    public boolean add(TreeNode node) {
        if (root == null) {
            root = node;
            return true;
        }

        TreeNode parent;
        TreeNode current = root;
        while (current != null) {
            parent = current;
            if (node.getValue() <= current.getValue()) {
                // 左节点
                current = current.getLeft();
                if (current == null) {
                    parent.setLeft(node);
                    return true;
                }
            } else {
                // 右节点
                current = current.getRight();
                if (current == null) {
                    parent.setRight(node);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public TreeNode get(int value) {
        if (root == null) {
            return null;
        }

        TreeNode current = root;
        while (current != null) {
            if (value == current.getValue()) {
                return current;
            } else if (value < current.getValue()) {
                current = current.getLeft();
            } else if (value > current.getValue()) {
                current = current.getRight();
            }
        }

        return null;
    }

    /**
     * 中序遍历
     *
     * @param treeNodeList 返回有序的list
     */
    public void sequentialTraversal(TreeNode root, List<TreeNode> treeNodeList) {
        if (root == null) {
            return;
        }
        sequentialTraversal(root.getLeft(), treeNodeList);
        treeNodeList.add(root);
        sequentialTraversal(root.getRight(), treeNodeList);
    }

    public void sequentialTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        sequentialTraversal(root.getLeft());
        System.out.print(root.getValue() + " ");
        sequentialTraversal(root.getRight());
    }

    /**
     * 把有序数组转换成二叉树
     *
     * @param root
     * @param list
     * @param left
     * @param right
     */
    public void arrayToTree(BinaryTree root, List<TreeNode> list, int left, int right) {
        if (left > right) {
            return;
        }
        int mid = (left + right) / 2;
        root.add(list.get(mid));
        arrayToTree(root, list, left, mid - 1);
        arrayToTree(root, list, mid + 1, right);
    }

    public TreeNode getRoot() {
        return root;
    }

}
