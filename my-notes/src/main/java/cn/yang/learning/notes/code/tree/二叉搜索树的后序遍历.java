package cn.yang.learning.notes.code.tree;

import cn.yang.learning.notes.code.Base;
import org.junit.Test;

// 题目要求：
// 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果，
// 假设输入数组的任意两个数都互不相同。
public class 二叉搜索树的后序遍历 extends Base {

    @Test
    public void test1() {
        // 后续遍历：左、右、根
        // 二叉搜索树，根左边的都比根小，根右边的都比根大
        //            8
        //          /   \
        //         6     10
        //       /  \   / \
        //      5    7 9   11
        int[] data = {5, 7, 6, 9, 11, 11, 8};
        int length = data.length;
        logLN("" + do1(data, 0, length));
    }


    // 用递归去判断
    private boolean do1(int[] data, int start, int lenth) {
        if (lenth <= 1) {
            return true;
        }
        int index = -1;
        for (int i = start; i < lenth - 1; ++i) {
            if (data[i] > data[lenth - 1]) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            // 值都比根小
            return do1(data, 0, lenth - 1);
        } else {
            // 左右子节点都有
            for (int i = index; i < lenth - 1; ++i) {
                if (data[i] < data[lenth - 1]) {
                    return false;
                }
            }
            return do1(data, 0, index) && do1(data, index, index - 1);
        }
    }


}
