package cn.yang.learning.notes.note2025算法.矩阵;

import java.util.HashSet;
import java.util.Set;

public class 矩阵置零 {

    public void setZeroes(int[][] matrix) {
        int hang = matrix.length;
        int lie = matrix[0].length;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for (int i=0; i<hang; ++i) {
            for (int j=0; j<lie; ++j) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i=0; i<hang; ++i) {
            for (int j=0; j<lie; ++j) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }

            }
        }

    }

}
