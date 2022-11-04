package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.TreeNode;
import org.junit.Test;

// 根据二叉树的前序遍历和中序遍历，重建该二叉树。
public class 重建二叉树 extends 二叉树的遍历 {

    @Test
    public void test1() {
        int[] qian = {1, 2, 3, 4, 5};
        int startQ = 0;
        int[] zhong = {2, 1, 4, 3, 5};
        int startZ = 0;
        int length = 5;
        TreeNode root = reconstruct(qian, startQ, zhong, startZ, length);
        logLN("前序遍历：根、左、右");
        qian(root);
        println();
        logLN("中序遍历：左、根、右");
        zhong(root);
    }

    private TreeNode reconstruct(int[] qian, int startQ, int[] zhong, int startZ, int length) {
        TreeNode<Integer> root = new TreeNode(qian[startQ]);
        if (length == 1) {
            return root;
        }
        int rootIndex = -1;
        for (int i = startZ; i < startZ + length; ++i) {
            if (zhong[i] == root.getVal()) {
                rootIndex = i;
                break;
            }
        }
        int lengthZuo = rootIndex - startZ;
        int lengthYou = length - rootIndex - 1 + startZ;
        root.setLeft(reconstruct(qian, startQ + 1, zhong, startZ, lengthZuo));
        root.setRight(reconstruct(qian, startQ + lengthZuo + 1, zhong, rootIndex + 1, lengthYou));
        return root;
    }

}
