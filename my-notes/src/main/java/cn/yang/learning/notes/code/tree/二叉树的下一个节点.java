package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import lombok.Data;
import org.junit.Test;

// 给定二叉树和其中一个节点，找到中序遍历序列的下一个节点。
// 树中的节点除了有左右孩子指针，还有一个指向父节点的指针。
public class 二叉树的下一个节点 extends Base {

    @Test
    public void test1() {
        //            1
        //          // \\
        //         2     3
        //       // \\
        //      4     5
        //    中序遍历->42513
        TreeNode2 root = new TreeNode2(1);
        root.left = new TreeNode2(2);
        root.left.father = root;
        root.right = new TreeNode2(3);
        root.right.father = root;
        root.left.left = new TreeNode2(4);
        root.left.left.father = root.left;
        root.left.right = new TreeNode2(5);
        root.left.right.father = root.left;

        // 节点5
        TreeNode2 node3 = root.left.right;

        if (node3.getRight() != null) {
            TreeNode2 cur = node3.getRight().getLeft();
            while (true) {
                if (cur == null) {
                    logLN(node3.getRight().getVal());
                    break;
                }
                cur = cur.getLeft();
            }
        } else {
            // 右节点为空
            if (node3 == node3.getFather().getLeft()) {
                // 节点是父的左
                logLN(node3.getFather().getVal());
            } else {
                // 节点是父的右
                TreeNode2 cur = node3.getFather();
                while (true) {
                    if (cur.getFather() != null && cur == cur.getFather().left) {
                        logLN(cur.getFather().getVal());
                        break;
                    }
                    cur = cur.getFather();
                    if (cur == null) {
                        break;
                    }
                }
            }
        }
    }


}

//带有父指针的二叉树节点
@Data
class TreeNode2 {
    public int val;
    public TreeNode2 left;
    public TreeNode2 right;
    public TreeNode2 father;

    public TreeNode2(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.father = null;
    }
}

