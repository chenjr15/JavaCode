package leetcode.p102;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class P102 {
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedHashMap<Integer,Integer> k ;
        if (root == null) {
            return result;
        }
        ArrayList<TreeNode> cur = new ArrayList<>();
        cur.add(root);
        ArrayList<TreeNode> next = new ArrayList<>();
        ArrayList<TreeNode> t;
        while (cur.size() > 0) {
            result.add(cur.stream().map((e) -> e.val).collect(Collectors.toList()));
            for (TreeNode node : cur) {
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }

            t = cur;
            cur = next;
            next = t;
            next.clear();
        }
        return result;
    }
}