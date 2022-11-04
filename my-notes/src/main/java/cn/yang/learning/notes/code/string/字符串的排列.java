package cn.yang.learning.notes.code.string;

import cn.yang.learning.notes.code.Base;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 字符串的排列 extends Base {

    @Test
    public void test() {
        logLN("" + checkInclusion("abcdxabcde", "abcdeabcdx"));
    }

    private void do1(char[] chars, int start, int end, List<String> list) {
        if (start == end) {
            logArray(chars);
            list.add(String.valueOf(chars));
            return;
        }
        for (int i = start; i < end; ++i) {
            swap(chars, i, start);
            do1(chars, start + 1, end, list);
            swap(chars, i, start);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1 == "" || s2 == "") {
            return false;
        }

        List<String> list = new ArrayList<>();
        do1(s1.toCharArray(), 0, s1.length(), list);

        for (String s : list) {
            boolean flag = false;
            char[] chars = s.toCharArray();
            char[] chars1 = s2.toCharArray();

            int index = 0;
            for (int i = 0; i < chars1.length; ++i) {
                if (index == chars.length) {
                    return true;
                }
                if (chars1[i] == chars[index]) {
                    ++index;
                    flag = true;
                } else {
                    index = 0;
                    flag = false;
                }
            }
            if (index != chars.length) {
                flag = false;
            }
            if (flag) {
                logLN("----------------" + s);
                return true;
            }
        }

        return false;
    }

}
