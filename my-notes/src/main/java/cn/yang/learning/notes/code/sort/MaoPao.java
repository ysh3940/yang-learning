package cn.yang.learning.notes.code.sort;

/**
 * 冒泡排序
 */
public class MaoPao {

    private void maoPao(int[] array) {
        boolean change = false;
        for (int i = 0; i < array.length - 1; ++i) {
            for (int j = 0; j < array.length - 1 - i; ++j) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    change = true;
                }
            }
            if (change == false) {
                break;
            }
            change = false;
        }
    }

}
