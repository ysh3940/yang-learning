package cn.yang.learning.notes.code;

import org.junit.Test;

public class 正整数反转 {

    @Test
    public void test1() {
        int num = 123456;
        int temp = 0;
        int sum = 0;
        while (true) {
            temp = num % 10;
            sum = sum * 10 + temp;

            num = num / 10;
            System.out.println(num);
            if (num <= 0) {
                break;
            }
        }

        System.out.println(sum);
    }

}
