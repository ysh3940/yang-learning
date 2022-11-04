package cn.yang.learning.notes.code.string;

import cn.yang.learning.notes.code.Base;
import org.junit.Test;

public class 无重复字符的最长子串 extends Base {

    @Test
    public void test() {
        String s = "dvdf";
        logLN(lengthOfLongestSubstring(s));
    }


    // 输入: "pwwkew"
    //输出: 3
    //解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
    //     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return 0;
        }
        int count = 1;
        int indexS = 0;
        int indexE = 0;
        L:
        for (int i = 0; i < chars.length; ++i) {
            if (i == 0) {
                indexE = 1;
                continue;
            }
            if (chars[i] == chars[i - 1]) {
                count = count > (indexE - indexS) ? count : (indexE - indexS);
                indexS = i;
                indexE = i + 1;
            } else {
                for (int j = indexS; j < indexE; ++j) {
                    if (chars[j] == chars[i]) {
                        count = count > (indexE - indexS) ? count : (indexE - indexS);
                        indexS = j + 1;
                        indexE = i + 1;
                        continue L;
                    }
                }
                indexE = i + 1;
            }
        }
        return count > (indexE - indexS) ? count : (indexE - indexS);
    }

}
