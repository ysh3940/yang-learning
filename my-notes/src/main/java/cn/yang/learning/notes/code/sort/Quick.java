package cn.yang.learning.notes.code.sort;

/**
 * 快速排序
 */
public class Quick {

    private void quick(int[] array, int left, int right) {
        if (array == null || array.length == 0) {
            return;
        }
        if (right == 0) {
            return;
        }
        if (left == array.length - 1) {
            return;
        }
        int leftInit = left;
        int rightInit = right;

        int mid;
        boolean fromRight = true;
        while (left != right) {
            if (array[left] > array[right]) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                fromRight = fromRight == false;
            }
            if (fromRight == false) {
                ++left;
            } else {
                --right;
            }
        }
        mid = left;
        if (mid > leftInit) {
            quick(array, leftInit, mid - 1);
        }
        if (mid < rightInit) {
            quick(array, mid + 1, rightInit);
        }
    }

}
