package cn.yang.learning.notes.note2025.普通数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 合并区间 {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <1) {
            return intervals;
        }

        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return 0;
            }
            return o1[0] > o2[0] ? 1 : -1;
        });

        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);

        for (int i=1; i<intervals.length; ++i) {
            int[] curr = intervals[i];
            int[] last = list.get(list.size()-1);
            if (curr[0] <= last[1]) {
                last[1] = Math.max(curr[1], last[1]);
            } else {
                list.add(curr);
            }
        }

        return list.toArray(new int[list.size()][]);
    }

}
