package leetcode.p310;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class P310 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for (String arg : args) {
            testFile(solution, arg);
        }
        System.out.println(solution.findMinHeightTrees(6, new int[][]{{1, 2}, {0, 2}, {2, 3}, {1, 4}, {5, 1}}));
        System.out.println(solution.findMinHeightTrees(6, new int[][]{{1, 0}, {0, 2}, {0, 3}, {3, 4}, {5, 4}}));
        System.out.println(solution.findMinHeightTrees(6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}}));
        System.out.println(solution.findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}));
    }

    public static void testFile(Solution s, String filename) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(filename));

            int n = scanner.nextInt();
            int[][] edges = new int[n - 1][];
            scanner.nextLine();
            final int[] i = {0};
            // ArrayList<int[]>
            JSONArray objects = JSONObject.parseArray(scanner.nextLine());
            objects.forEach((e) -> {
                JSONArray arr = (JSONArray) e;
                edges[i[0]] = new int[]{arr.getInteger(0), arr.getInteger(1)};
                i[0]++;
            });
            List<Integer> minHeightTrees = s.findMinHeightTrees(n, edges);
            System.out.println(minHeightTrees);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}

class Solution {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (n < 1) {
            return result;
        }
        if(n==1){
            result.add(0);
            return  result;
        }
        if (n == 2) {
            // 极端情况
            result.add(edges[0][0]);
            result.add(edges[0][1]);
            return result;
        }

        // 采用类似拓扑排序的办法。
        // 从所有叶节点进行bfs，找到离所有叶节点最近的节点，那么肯定就是树根
        // 而叶节点，其实就是度为1的节点。

        // 下面是先构建邻接表，
        ArrayList<ArrayList<Integer>> edgeMap;

        // 入度，计算有多少个节点指向这个系欸但
        int[] inDegree = new int[n];

        edgeMap = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            edgeMap.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            edgeMap.get(edge[0]).add(edge[1]);
            edgeMap.get(edge[1]).add(edge[0]);
            inDegree[edge[0]]++;
            inDegree[edge[1]]++;
        }

        /* 接下去开始从叶节点往中间BFS */
        // 首先查找叶节点
        // int[][] edgeCnt = new int[n][2];
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (edgeMap.get(i).size() == 1) {
                queue.add(i);
                visited[i] = true;
            }
        }
        int curLevelCnt;

        // 开始 拓扑排序, 结果是bfs 的最后一层. 一层层遍历，
        // 每次都把已有结果清楚并把当前层所有节点加入结果集，最后只会留下最后一层.
        // 注意只能把入度为1 的节点加入，也就是叶子节点，每次从叶子节点开始剥。
        while (queue.size() > 0) {
            curLevelCnt = queue.size();
            result.clear();
            result.addAll(queue);
            // 将当前层全部出队
            for (int i = 0; i < curLevelCnt; i++) {
                Integer node = queue.poll();
                for (Integer next : edgeMap.get(node)) {
                    // 只能加新的叶子节点，其实就是拓扑排序
                    inDegree[next]--;
                    if (inDegree[next] == 1)
                        queue.offer(next);
                }
            }
        }
        return result;

    }
}