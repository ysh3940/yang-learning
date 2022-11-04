package cn.yang.learning.notes.code;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class 剑指offer46把数字翻译成字符串 extends Base {

    @Test
    public void test() {
        logLN(getTranslationCount("10"));

        System.out.println(getTranslationCount("0") == 1);
        System.out.println(getTranslationCount("10") == 2);
        System.out.println(getTranslationCount("12258") == 5);
        System.out.println(getTranslationCount("-100") == 0);
    }

    // https://www.cnblogs.com/yongh/p/9950362.html
    // 题目　
    //　　给定一个数字，我们按照如下规则把它翻译为字符串：0翻译成"a"，1翻译成"b"，……，11翻译成"l"，……，25翻译成"z"。一个数字可能有多个翻译。
    // 例如12258有5种不同的翻译，它们分别"bccfi", "bwfi", "bczi", "mcfi" 和"mzi" 。请编程实现一个函数用来计算一个数字有多少种不同的翻译方法。
    public int getTranslationCount(String number) {
        if (StringUtils.isBlank(number)) {
            return 0;
        }
        if (Integer.valueOf(number) < 0) {
            return 0;
        }
        if (number.length() == 1) {
            return 1;
        }

        int c1 = getTranslationCount(number.substring(1, number.length()));
        int c2 = 0;
        if (number.length() == 2 && canBeTrans(number.substring(0, 2))) {
            c2 = 1;
        }
        if (number.length() > 2 && canBeTrans(number.substring(0, 2))) {
            c2 = getTranslationCount(number.substring(2, number.length()));
        }

        return c1 + c2;
    }

    private boolean canBeTrans(String sNumber) {
        int convert = Integer.valueOf(sNumber);
        if (convert >= 10 && convert <= 25) {
            return true;
        }
        return false;
    }

}
