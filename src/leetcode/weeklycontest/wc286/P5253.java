package leetcode.weeklycontest.wc286;

import java.util.Arrays;

public class P5253 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        long[] longs ;
        longs = solution.kthPalindrome(new int[]{1,2,3,4,5,90},3);
        System.out.println(Arrays.toString(longs));
        longs =  solution.kthPalindrome(new int[]{2,4,6},4);
        System.out.println(Arrays.toString(longs));

        longs = solution.kthPalindrome(new int[]{2, 4, 6}, 10);
        System.out.println(Arrays.toString(longs));


    }
}


class Solution {
    /**
     * 给你一个整数数组 queries 和一个 正 整数 intLength ，请你返回一个数组 answer ，
     * 其中 answer[i] 是长度为 intLength 的 正回文数 中第 queries[i] 小的数字，如果不存在这样的回文数，则为 -1 。
     * 回文数 指的是从前往后和从后往前读一模一样的数字。回文数不能有前导 0 。
     * 1 <= queries.length <= 5 * 104
     * 1 <= queries[i] <= 109
     * 1 <= intLength <= 15
     *
     * @param queries   1 <= queries[i] <= 109, 1 <= queries.length <= 5 * 104
     * @param intLength 1 <= intLength <= 15
     * @return 其中 answer[i] 是长度为 intLength 的 正回文数 中第 queries[i] 小的数字，如果不存在这样的回文数，则为 -1 。
     */
    public long[] kthPalindrome(int[] queries, int intLength) {
        long[] answer = new long[queries.length];
        // 中间的位数， 取值范围是0-9 共十位，
        long centerLen = (intLength - 1) / 2 ;
        // 个数最多为，头尾的取值范围是1-9 共9位，乘上 中间的位数，每位有10个选项
        long maxCnt = (long) (9 * Math.pow(10, centerLen));
        byte [] answerParts  = new byte[intLength];

        for (int i = 0, queriesLength = queries.length; i < queriesLength; i++) {
            int q = queries[i];
            // 计算每个查询
            if (q > maxCnt) {
                answer[i] = -1;
            }else{
                q--;
                // 先计算一半, 计算右边
                int right = intLength /2 ;
                while(right<intLength-1){
                    answerParts[right] = (byte) (q%10);
                    q/=10;
                    right++;
                }
                answerParts[intLength-1]= (byte) (q+1);

                //
                for (int l =0,r=intLength-1;l<r;l++,r--){
                    answerParts[l] = answerParts[r];
                }
                // 转换成long
                answer[i]= 0;
                for (byte part : answerParts) {
                    answer[i]*=10;
                    answer[i]+=part;
                }
                // System.out.println("q = "+(queries[i])+" "+Arrays.toString(answerParts)+answer[i]);
            }
        }
        return answer;
    }
}