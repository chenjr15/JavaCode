package leetcode.swordsoffer2.p74;

import java.util.ArrayList;
import java.util.Arrays;

public class P74 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // solution.merge()
    }
}

class Solution {
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> merged = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        // System.out.println(intervals);
        int start, end;
        start = intervals[0][0];
        end = intervals[0][1];
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] < end) {
                // 起点在现有区间的终点结束之前，因此肯定要合并
                // 判断其结尾是否扩大当前区间。
                if (intervals[i][1] > end) {
                    end = intervals[i][1];
                }
            } else {
                //  新区间
                merged.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }

        }
        merged.add(new int[]{start, end});
        int[][] ints = merged.toArray(new int[][]{});
        return ints;

    }
}