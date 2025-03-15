package cn.yang.learning.notes.note2025算法.滑动窗口;

import java.util.ArrayList;
import java.util.List;

public class 找到字符串中所有字母异位词 {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }

        int[] pChar = new int[26];
        for (char ch : p.toCharArray()) {
            pChar[ch - 'a']++;
        }

        int[] sChar = new int[26];
        for (int i=0; i<s.length(); ++i) {
            sChar[s.charAt(i) - 'a']++;
            if (i > p.length()) {
                sChar[s.charAt(i-p.length()) - 'a']--;
            }
            if (isEqual(pChar, sChar)) {
                res.add(i-p.length() + 1);
            }
        }

        return res;
    }

    private boolean isEqual(int[] arr1, int[] arr2) {
        for (int i=0; i<26; ++i) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }


}
