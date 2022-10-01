package leetcode.weeklycontest.wc289;

import java.util.ArrayList;
import java.util.Arrays;

public class p6073 {
}

class Solution {
    /**
     * longestPath 6073. 相邻字符不同的最长路径 显示英文描述
     * 给你一棵 树（即一个连通、无向、无环图），根节点是节点 0 ，这棵树由编号从 0 到 n - 1 的 n 个节点组成。
     * 用下标从 0 开始、长度为 n 的数组 parent 来表示这棵树，其中 parent[i] 是节点 i 的父节点，由于节点 0 是根节点，所以 parent[0] == -1 。
     * <p>
     * 另给你一个字符串 s ，长度也是 n ，其中 s[i] 表示分配给节点 i 的字符。
     * <p>
     * 请你找出路径上任意一对相邻节点都没有分配到相同字符的 最长路径 ，并返回该路径的长度。
     *
     * @param parent
     * @param s
     * @return
     */
    public int longestPath(int[] parent, String s) {
        int lp = 0;
        boolean[] isParent =  new boolean[parent.length];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(parent.length);
        for (int i = 0; i < parent.length; i++) {
            graph.add(new ArrayList<>());
        }
        // 考虑将相同父子相同的边断掉， 这样后面的路径就不会有相邻相同的节点了。
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1){
                continue;
            }
            if ( s.charAt(i) == s.charAt(parent[i])) {
                // 断开边
                parent[i] = -1;
            }
            // 构建双向图
            graph.get(i).add(parent[i]);
            graph.get(parent[i]).add(i);

        }
        int[][] maxChildren = new int[parent.length][2];
        // 无向图最长路径。
        // 断开边之后会得到很多的树，我们需要在这些树中去寻找长度最大路径
        // 最大的路径应该是从根节点，到最长的边相加
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1) {
                continue;
            }
            //  去找到他的父节点
            // parent[i]+=
        }
        return lp;
    }
}