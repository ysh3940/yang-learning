package cn.yang.learning.notes.code.string;

import cn.yang.learning.notes.code.Base;
import org.junit.Test;

public class 正则表达式匹配 extends Base {

    // 面试题19：正则表达式匹配
    //
    //题目要求：
    //实现正则表达式中.和*的功能。.表示任意一个字符，*表示他前面的字符的任意次（含0次）。比如aaa与a.a和ab*ac*a匹配，但与aa.a和ab*a不匹配。

    @Test
    public void test1() {
//    logLN(match("aaa".toCharArray(), "a.a".toCharArray()));
        System.out.println(match("aaa".toCharArray(), "a.a".toCharArray()));//true
        System.out.println(match("aaa".toCharArray(), "ab*ac*a".toCharArray()));//true
        System.out.println(match("aaa".toCharArray(), "aa.a".toCharArray()));//false
        System.out.println(match("aaa".toCharArray(), "ab*a".toCharArray()));//false
    }

    private boolean match(char[] str, char[] pattern) {
        if (str == null && pattern == null) {
            return true;
        }
        if (str == null || pattern == null) {
            return false;
        }
        if (str.length == 0 && pattern.length == 0) {
            return true;
        }
        if (str.length == 0 || pattern.length == 0) {
            return false;
        }

        return matchCore(str, 0, pattern, 0);
    }

    private boolean matchCore(char[] str, int indexStr, char[] pattern, int indexPat) {
        if (str.length == indexStr && pattern.length == indexPat) {
            return true;
        }
        if (str.length > indexStr && pattern.length == indexPat) {
            return false;
        }


        if ((indexPat + 1) < pattern.length && pattern[indexPat + 1] == '*') {
            if (str[indexStr] == pattern[indexPat] || (pattern[indexPat] == '.')) {
                return matchCore(str, indexStr + 1, pattern, indexPat + 2) ||
                        matchCore(str, indexStr + 1, pattern, indexPat) ||
                        matchCore(str, indexStr, pattern, indexPat + 2);
            } else {
                return matchCore(str, indexStr, pattern, indexPat + 2);
            }
        }

        if (str.length == indexStr && pattern.length > indexPat) {
            return false;
        }

        if (str[indexStr] == pattern[indexPat] || (pattern[indexPat] == '.')) {
            return matchCore(str, indexStr + 1, pattern, indexPat + 1);
        }

        return false;
    }

}
