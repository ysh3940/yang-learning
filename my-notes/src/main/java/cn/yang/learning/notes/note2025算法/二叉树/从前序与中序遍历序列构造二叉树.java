package cn.yang.learning.notes.note2025算法.二叉树;

public class 从前序与中序遍历序列构造二叉树 {

    // preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    // preorder = [1,2,3], inorder = [2,1,3]
    // preorder = [1], inorder = [1]
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length ==0 || inorder.length ==0) {
            return null;
        }

        return buildTree2(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    public TreeNode buildTree2(int[] preorder, int leftPre, int rightPre,
                              int[] inorder, int leftIn, int rightIn) {
        if (leftPre > rightPre || leftIn > rightIn) {
            return null;
        }

        int rootVal = preorder[leftPre];
        int rootIndex = findRootIndex(inorder, leftIn, rightIn, rootVal);
        int leftSize = rootIndex-leftIn;

        TreeNode left = buildTree2(preorder, leftPre+1, leftPre+leftSize, inorder, leftIn, rootIndex-1);
        TreeNode right = buildTree2(preorder, leftPre+leftSize+1, rightPre, inorder, rootIndex+1, rightIn);


        TreeNode root = new TreeNode(rootVal);
        root.left = left;
        root.right = right;


        return root;
    }

    private int findRootIndex(int[] inorder, int leftIn, int rightI, int val) {

        for (int i=leftIn; i<rightI+1; ++i) {
            if (val == inorder[i]) {
                return i;
            }
        }

        return -1;
    }


}
