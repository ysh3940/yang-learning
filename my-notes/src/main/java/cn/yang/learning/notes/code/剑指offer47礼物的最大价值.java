package cn.yang.learning.notes.code;

import org.junit.Test;

public class 剑指offer47礼物的最大价值 {

    // 题目　
    //　　在一个m×n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于0）。
    // 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格直到到达棋盘的右下角。给定一个棋盘及其上面的礼物，请计算你最多能拿到多少价值的礼物？
    public int maxValueOfGifts(int[][] values, int rows, int cols, int row, int col) {
        if (values == null || values.length == 0 || values[0].length == 0) {
            return 0;
        }

        if (row == rows && col == cols) {
            return values[row][col];
        }

        int v1 = 0;
        if (row + 1 < rows) {
            v1 = maxValueOfGifts(values, rows, cols, row + 1, col);
        }
        int v2 = 0;
        if (col + 1 < cols) {
            v2 = maxValueOfGifts(values, rows, cols, row, col + 1);
        }

        return values[row][col] + Math.max(v1, v2);
    }

    @Test
    public void test() {
        int[][] arr = {
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}
        };
        System.out.println(maxValueOfGifts(arr, arr.length, arr[0].length, 0, 0));
    }


}
