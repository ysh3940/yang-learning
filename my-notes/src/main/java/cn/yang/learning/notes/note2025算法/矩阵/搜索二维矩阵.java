package cn.yang.learning.notes.note2025算法.矩阵;

public class 搜索二维矩阵 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int hang = matrix.length;
        int lie = matrix[0].length;

        int i = 0;
        int j = lie-1;
        while (i<hang && j>=0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                --j;
            } else {
                ++i;
            }
        }

        return false;
    }


}
