package cn.yang.learning.notes.code.string;

import cn.yang.learning.notes.code.Base;
import org.junit.Test;

public class 最长公共前缀 extends Base {

    @Test
    public void test() {
        logLN(longestCommonPrefix(new String[]{"c", "c", "c"}));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int sLength = 0;
        String ss = "";
        for (int i = 0; i < strs.length; ++i) {
            if (i == 0) {
                sLength = strs[i].length();
                ss = strs[0];
            }
            if (strs[i].length() < sLength) {
                sLength = strs[i].length();
                ss = strs[i];
            }
        }

        if (sLength == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        L:
        for (int j = 0; j < sLength; ++j) {
            char s = ss.charAt(j);
            builder.append(s);
            for (int i = 0; i < strs.length; ++i) {
                if (strs[i].startsWith(builder.toString()) == false) {
                    flag = true;
                    break L;
                }
            }
        }

        return flag ? builder.substring(0, builder.length() - 1) : builder.toString();
    }

}
