package cn.yang.learning.notes.code;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出数组中最大的3个数字
 */
public class Array3Sum extends Base {

    // 题目
    //　　把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
    @Test
    public void test2() {
        do1(6, new ArrayList<>());
    }

    // 遍历所有出现的可能性
    private void do1(int count, List<Integer> list) {
        if (count < 1) {
            return;
        }

        for (int i = 1; i <= 6; ++i) {
            list.add(i);
            do1(count - 1, list);
            if (count == 1) {
                logArray(list.toArray());
            } else {
                logLN("");
            }
            list.remove(Integer.valueOf(i));
        }
    }

    @Test
    public void test() {
        int[] array = {2, 31, 4, 5, 1, 1, 10, 50, 1, 5, 5};
        boolean flag = false;
        for (int i = 0; i < array.length - 1; ++i) {
            for (int j = 0; j < array.length - 1 - i; ++j) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    flag = true;
                }
            }
            if (flag == false || i == 2) {
                break;
            }
            flag = false;
        }

        for (int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        System.out.print(myPow(0.00001, 2147483647) + " ");
    }

    // 数值的整数次方
    // 题目描述
    // 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。
    public double myPow(double x, int n) {
        boolean flag = false;
        if (n < 0) {
            n = -n;
            flag = true;
        }
        double res = 1;
        for (int i = 1; i <= n; ++i) {
            res = res * x;
        }
        return flag ? 1 / res : res;
    }

}
