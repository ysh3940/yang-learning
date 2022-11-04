package cn.yang.learning.notes.code.tree;

import java.util.LinkedList;

/**
 * 层次遍历（广度遍历）
 * <p>
 * 层次遍历的代码比較简单。仅仅须要一个队列就可以。先在队列中增加根结点。之后对于随意一个结点来说。在其出队列的时候，訪问之。同一时候假设左孩子和右孩子有不为空的。入队列。
 */
public class LevelTraverse {

    public static void levelTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        // 加到队列尾部
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 从队列头部获取
            int num = queue.size();
            TreeNode node = queue.poll();
            --num;

            while (num >= 0) {
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
                if (num == 0) {
                    System.out.print(node.getValue() + "  ");
                    break;
                }
                node = queue.poll();
                --num;
            }
        }
    }

    public static void main(String[] args) {
    /*
           1
         2   3
       4
      上面二叉树，只打印从右面看得到的节点：1 3 4
     */
        System.out.println("------------------");
        TreeNode root = new TreeNode(1);

        TreeNode root2 = new TreeNode(2);
        root.setLeft(root2);

        TreeNode root3 = new TreeNode(3);
        root.setRight(root3);

        TreeNode root4 = new TreeNode(4);
        root2.setLeft(root4);

        levelTraverse(root);
    }


}
