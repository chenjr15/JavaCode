package leetcode.weeklycontest.wc287;

import java.util.Arrays;

public class P5219 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int k = 3;
        int[] candies = {5, 8, 6};
        int max;
        max = solution.maximumCandies(candies, k);
        System.out.printf("%d %s %d\n", max, Arrays.toString(candies), k);

        k = 11;
        candies = new int[]{2, 5};
        max = solution.maximumCandies(candies, k);
        System.out.printf("%d %s %d\n", max, Arrays.toString(candies), k);
    }

    static class Solution {
        public int maximumCandies(int[] candies, long k) {
            int gotMax = 0;
            long sum = Arrays.stream(candies).sum();
            if (sum < k) {
                return 0;
            }
            Arrays.sort(candies);

            //if 数量大于 k
            if (candies.length > k) {
                if (sum < k * 2) {
                    return 1;
                } else {
                    // if ()
                    // 需要继续分

                }

            } else {
                // 数量有多的情况
                while (candies[candies.length - 1] / 2 > candies[(int) (candies.length - k)]){
                    candies[candies.length - 1]/=2;
                    candies[(int) (candies.length - k)] = candies[candies.length - 1];
                    Arrays.sort(candies);

                }
                gotMax =  candies[(int) (candies.length - k)];

            }


            return gotMax;

        }
    }

}
