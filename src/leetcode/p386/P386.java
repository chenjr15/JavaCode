package leetcode.p386;

import java.util.ArrayList;
import java.util.List;

public class P386 {
    public static void main(String[] args) {
        Solution solution= new Solution();
        System.out.println(solution.lexicalOrder(12));
        System.out.println(solution.lexicalOrder(1));
        System.out.println(solution.lexicalOrder(2));
        System.out.println(solution.lexicalOrder(122));
    }
}
class Solution {
    public List<Integer> lexicalOrder(int n) {

        ArrayList<Integer> result = new ArrayList<>(n);
        long cur = 1;
        while (result.size()<n){
            while (cur <=n){
                result.add((int) cur);
                cur*=10;
            }
            while(cur%10 ==9 || cur>n){
                cur/=10;
            }
            cur++;
        }

        return  result;

    }
}