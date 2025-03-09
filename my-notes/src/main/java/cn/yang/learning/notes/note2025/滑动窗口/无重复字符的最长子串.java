package cn.yang.learning.notes.note2025.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class 无重复字符的最长子串 {

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        int left = 0;
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i=0; i<length-1; ++i) {
            char ch = s.charAt(i);
            if (map.containsKey(ch) && map.get(ch) >= left) {
                left = map.get(ch)+1;
            }
            map.put(ch, i);
            maxLen = Math.max(maxLen, i - left + 1);
        }

        return maxLen;
    }

}
