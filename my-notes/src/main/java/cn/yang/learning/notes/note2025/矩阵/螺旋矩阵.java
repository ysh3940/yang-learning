package cn.yang.learning.notes.note2025.矩阵;

import java.util.ArrayList;
import java.util.List;

public class 螺旋矩阵 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length==0 || matrix[0].length==0) {
            return res;
        }

        int hang = matrix.length;
        int lie = matrix[0].length;
        int top = 0;
        int bot = hang-1;
        int left = 0;
        int right = lie - 1;

        while (top<=bot && left<=right) {
            for (int i=left; i<=right; ++i) {
                res.add(matrix[top][i]);
            }
            ++top;

            for (int i=top; i<=bot; ++i) {
                res.add(matrix[right][i]);
            }
            --right;

            if (top <= bot) {
                for (int i=right; i>=left; --i) {
                    res.add(matrix[bot][i]);
                }
                --bot;
            }

            if (left <= right) {
                for (int i=bot; i>=top; --i) {
                    res.add(matrix[left][i]);
                }
                ++left;
            }

        }

        return res;
    }


}
