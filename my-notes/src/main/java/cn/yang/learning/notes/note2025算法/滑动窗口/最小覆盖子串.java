package cn.yang.learning.notes.note2025算法.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class 最小覆盖子串 {

    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0)+1);
        }


        Map<Character, Integer> wind = new HashMap<>();

        int needCount = need.size();
        int validCount = 0;

        int left = 0;
        int right = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            ++right;
            if (need.containsKey(c)) {
                wind.put(c, wind.getOrDefault(c, 0)+1);
                if (need.get(c) == wind.get(c)) {
                    ++validCount;
                }
            }

            if (validCount == needCount) {
                if (right - left < len) {
                    start = left;
                    len = right-left;
                }

                char d = s.charAt(left);
                ++left;
                if (need.containsKey(d)) {
                    if (wind.get(d) == need.get(d)) {
                        --validCount;
                    }
                    wind.put(d, wind.get(d)-1);
                }

            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, len);
    }

}
