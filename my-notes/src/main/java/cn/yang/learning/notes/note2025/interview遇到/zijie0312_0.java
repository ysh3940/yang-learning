package cn.yang.learning.notes.note2025.interview遇到;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class zijie0312_0 {

    public static void main(String[] args) {
        int[][] nums = new int[3][3];
        nums[0] = new int[]{5,16,7};
        nums[1] = new int[]{14,9,18};
        nums[2] = new int[]{3,12,17};

        test1(nums);

        System.out.println(max);
    }

    static String max = "";


    // [5,6,7]
    // [4,9,8]
    // [3,2,1]
    // 搜索最长的路径，9，8，7 数字要依次递减
    public static int test1(int[][] nums) {
        int hang = nums.length;
        int lie = nums[0].length;

        for (int i=0; i<hang; ++i) {
            for (int j=0; j<lie; ++j) {
                dfs(nums, i, j, "", Integer.MAX_VALUE);
            }
        }

        return 0;
    }

    static Map<String, Integer> map = new HashMap<>();

    public static void dfs(int[][] nums, int h, int l, String par, Integer parVal) {
        int hang = nums.length;
        int lie = nums[0].length;

        if (h < 0 || h >= hang || l <0 || l >= lie) {
            return;
        }
        if (map.containsKey(h+"_"+l)) {
            return;
        }
        if (nums[h][l] >= parVal) {
            return;
        }

        map.put(h+"_"+l, nums[h][l]);

        String newPar;
        if (StringUtils.isBlank(par)) {
            newPar = nums[h][l]+"";
        } else {
            newPar = par+"-"+nums[h][l];
        }

//        System.out.println(newPar);
        max(newPar);

        dfs(nums, h-1, l, newPar, nums[h][l]);
        dfs(nums, h+1, l, newPar, nums[h][l]);
        dfs(nums, h, l-1, newPar, nums[h][l]);
        dfs(nums, h, l+1, newPar, nums[h][l]);


        map.remove(h+"_"+l);
    }


    public static void max(String cur) {
        String[] arrCur = cur.split("-");
        String[] arrMax = max.split("-");

        if (arrCur.length >= arrMax.length) {
            max = cur;
        }


    }



}
