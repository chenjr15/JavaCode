package datastructure.heapsort;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] nums = {47, 85, 51, 81, 95, 73, 72, 96, 11, 40, 61};
        test(nums);
        for (int i = 0; i < 10; i++) {
            System.out.println("### " + i);
            int n = (int) (Math.random() * 20) + 1;
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = (int) (Math.random() * 100);
            }
            test(arr);

        }

    }

    private static void test(int[] nums) {
        System.out.println(Arrays.toString(nums));
        heapsort(nums);
        System.out.println(Arrays.toString(nums));

        for (int p = 1; p < nums.length; p++) {
            if (nums[p] < nums[p - 1]) {
                System.out.printf("Wrong order!%d %d %n", nums[p - 1], nums[p]);
            }
        }
    }

    public static void heapsort(int[] nums) {
        int n = nums.length;
        // 建堆
        // root = (p-1)/2
        // left = 2*root +1
        // right = 2*left +2
        int p,root,t;
        while (n > 0) {
            p=n-1;
            // 从叶节点开始遍历
            while (p>0){
                root = (p-1)/2;
                if (nums[p]>nums[root]){
                    t = nums[p];
                    nums[p] = nums[root];
                    nums[root]= t;
                }
                --p;
            }
            --n;
            t = nums[0];
            nums[0] = nums[n];
            nums[n]= t;
        }
    }
}
