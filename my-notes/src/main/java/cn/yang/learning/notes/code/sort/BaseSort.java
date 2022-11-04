package cn.yang.learning.notes.code.sort;


import cn.yang.learning.notes.code.Base;

public class BaseSort extends Base {

    protected void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
