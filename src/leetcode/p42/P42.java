package leetcode.p42;

import java.util.Arrays;

public class P42 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int trap = solution.trap(new int[]{4,2,0,3,2,5});
        System.out.println(trap);
    }
}
class Solution {
    public int trap(int[] height) {

        int water = 0;
        int n = height.length;
        if (n<3){
            return water;
        }
        // 从做往右遍历得到的当前行之前的最大值
        int[] maxHeightLeft = new int[n];
        maxHeightLeft[0] = 0;
        int[] maxHeightRight = new int[n];
        maxHeightRight[n-1] = 0;
        for (int i=1;i<n;i++){
            maxHeightLeft[i] = Math.max(maxHeightLeft[i-1],height[i]);
            maxHeightRight[n-i-1] = Math.max(maxHeightRight[n -i],height[n-i-1]);
        }
        System.out.println(Arrays.toString(maxHeightLeft));
        System.out.println(Arrays.toString(maxHeightRight));
        for (int i=1;i<n-1;i++){
            // 有效高度
            int h = Math.min(maxHeightLeft[i],maxHeightRight[i]) - height[i];
            System.out.printf("[%d] %d \n",i,h);

            if (h>0){
                water+=h;
            }
        }
        return water;

    }
}