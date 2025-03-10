package cn.yang.learning.notes.note2025.二分查找;

public class 搜索二维矩阵 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }

        int hang = matrix.length;
        int lie = matrix[0].length;
        for (int i=0; i<hang; ++i) {
            if (matrix[i][lie-1] < target) {
                continue;
            }
            int left = 0;
            int right = lie-1;
            while (left <= right) {
                int mid = (left+right)/2;
                if (matrix[i][mid] == target) {
                    return true;
                } else if (matrix[i][mid] < target){
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            }

        }

        return false;
    }

}
