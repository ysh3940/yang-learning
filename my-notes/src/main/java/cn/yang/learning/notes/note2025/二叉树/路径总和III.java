package cn.yang.learning.notes.note2025.二叉树;

import java.util.HashMap;
import java.util.Map;

public class 路径总和III {

    Map<Long, Integer> map = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        map.put(0L,1);
        return pathSum2(root, targetSum, 0);
    }

    public int pathSum2(TreeNode root, int targetSum, long sumCurr) {
        if (root == null) {
            return 0;
        }

        sumCurr += root.val;
        int count = map.getOrDefault(sumCurr - targetSum, 0);
        map.put(sumCurr, map.getOrDefault(sumCurr, 0) + 1);

        count += pathSum2(root.left, targetSum, sumCurr);
        count += pathSum2(root.right, targetSum, sumCurr);

        map.put(sumCurr, map.get(sumCurr) - 1);

        return count;
    }


}
