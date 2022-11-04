package cn.yang.learning.notes.code.sort;

import cn.yang.learning.notes.code.Base;
import org.junit.Test;

public class 冒泡排序 extends Base {

    //数组冒泡，时间o(n^2)，空间o(1)，稳定

    // 2
    // 1
    // 3
    // 4
    // 5（底下最小或最大的数字一直往上冒泡，第一次之后，最上面的是最小或最大）
    public static void bubbleSort(int[] data) {
        if (data == null || data.length <= 1)
            return;

        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 1; j < data.length - i; j++) {
                if (data[j - 1] > data[j]) {
                    int temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }

    }

    @Test
    public void testBubbleSort() {
        int[] data = {5, 4, 3, 1, 2};
        bubbleSort(data);
        System.out.print("数组冒泡排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }


}
