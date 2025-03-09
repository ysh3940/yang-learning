package cn.yang.learning.notes.note2025.哈希表;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 字母异位词分组 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i=0; i<strs.length; ++i) {
            int[] count = new int[26];
            for (char c : strs[i].toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder temp = new StringBuilder();
            for (int k=0; i<count.length; ++i) {
                temp.append(count[k]).append("#");
            }

            List<String> data = map.getOrDefault(temp.toString(), new ArrayList<>());
            data.add(strs[i]);

        }


        return new ArrayList<>(map.values());
    }


}
