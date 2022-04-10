package leetcode.weeklycontest.wc286;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P5269 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> piles = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 100, 3),
                Arrays.asList(7, 8, 9))
        );
        System.out.println(solution.maxValueOfCoins(piles,2));
        System.out.println(solution.maxValueOfCoins(piles,3));
        System.out.println(solution.maxValueOfCoins(piles,1));
        // long[] longs ;
        // longs = solution.maxValueOfCoins(new ArrayList<>(),3);
        // System.out.println(Arrays.toString(longs));
        // longs =  solution.kthPalindrome(new int[]{2,4,6},4);
        // System.out.println(Arrays.toString(longs));
        //
        // longs = solution.kthPalindrome(new int[]{2, 4, 6}, 10);
        // System.out.println(Arrays.toString(longs));


    }

    static class Solution {
        Integer[][] coinsPrefix;
        int maxvalue = 0;

        public int maxValueOfCoins(List<List<Integer>> piles, int k) {

            coinsPrefix = new Integer[piles.size()][];
            // 计算前缀和
            for (int j = 0; j < piles.size(); j++) {
                List<Integer> pile = piles.get(j);
                coinsPrefix[j] = new Integer[pile.size()];
                pile.toArray(coinsPrefix[j]);
                int sum = 0;
                for (int i = 0; i < pile.size(); i++) {
                    sum += coinsPrefix[j][i];
                    coinsPrefix[j][i] = sum;
                }
            }

            return maxvalue;
        }

        public void dfs(int left, int curPile, int curValue) {
            if (left == 0) {
                if (curValue > maxvalue) {
                    maxvalue = curValue;
                }
                return;
            }
            if (curPile == coinsPrefix.length) {
                //错误选项
                return;
            }
            //不选的情况
            dfs(left, curPile + 1, curValue);

            // if (curValue)
            // 当前节点选择前i个硬币
            int canChose = Math.min(coinsPrefix[curPile].length, left - 1);
            for (int i = 1; i < canChose; i++) {
                // curValue+ ;

                dfs(left - i, curPile + 1, curValue + coinsPrefix[curPile][i - 1]);
            }

        }
    }
}
