package cn.yang.learning.notes.note2025.矩阵;

public class 旋转图像 {

    public void rotate(int[][] matrix) {
        int[][] temp = new int[matrix.length][matrix.length];

        int l = matrix.length;
        for (int i=0; i<l; ++i) {
            for (int j=0; i<l; ++i) {
                temp[j][l-i-1] = matrix[i][j];
            }
        }

        for (int i=0; i<l; ++i) {
            for (int j=0; i<l; ++i) {
                matrix[i][j] = temp[i][j];
            }
        }


    }


    public void rotate2(int[][] matrix) {
        int l = matrix.length;
        for (int i=0; i<l; ++i) {
            for (int j=0; i<l; ++i) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i=0; i<l; ++i) {
            for (int j=0; i<l/2; ++i) {
                int temp =  matrix[i][j];
                matrix[i][j] = matrix[i][l-j-1];
                matrix[i][l-j-1] = temp;
            }
        }


    }


}
