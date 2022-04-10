package datastructure.quicksort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1,1,23};
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
        quicksort(nums);
        System.out.println(Arrays.toString(nums));

        for (int p = 1; p < nums.length; p++) {
            if (nums[p] < nums[p - 1]) {
                System.out.printf("Wrong order!%d %d %n", nums[p - 1], nums[p]);
            }
        }
    }

    public static void quicksort(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
    }

    public static void quicksort(int[] nums, int left, int right) {
        /*
         *  快排的思想是将数组分为三个部分,一个是主元pivot, 比p小的,比p大的
         * | 小于 pivot |pivot| 大于pivot |
         * 然后对左右递归处理,即可得排序完的结果
         * 如果主元正好能够平分两边的话效率就会很高 O(nlogn) 最坏的情况是每次都有一边是空的,会导致快排变成O(n^2)
         */
        // 结束条件
        // if (right < left) {
        //     return;
        // }
        // int p = partition(nums, left, right);
        // // 递归处理左右
        // quicksort(nums, 0, p-1);
        // quicksort(nums, p + 1, right);
        int p;
        // 利用 while 循环优化掉一个递归
        while (right > left) {
            p = partition(nums, left, right);
            // 递归处理左右
            quicksort(nums, 0, p - 1);
            left = p + 1;
            // quicksort(nums, p + 1, right);
        }

    }


    public static int partition(int[] nums, int left, int right) {
        if (right - left <= 0) {
            return left;
        }
        int t;
        // 快排划分
        int i = left;
        int j = right - 1;
        while (true) {
            // 注意这里在陈越老师的实现中是通过median3设置哨兵保证不会越界的, 我们没搞这个就直接用边界判断了
            while (i <= right && nums[i] < nums[right]) {
                ++i;
            }
            while (j >= left && nums[j] > nums[right]) {
                --j;
            }
            // 交换i和j
            if (i < j) {
                t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                ++i;
                --j;
            } else {
                break;
            }
        }
        // 结束后i指向的就是主元位置，将主元换过来
        t = nums[i];
        nums[i] = nums[right];
        nums[right] = t;
        return i;
    }
}
