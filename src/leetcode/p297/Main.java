package leetcode.p297;

import datastructure.TreeNode;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(3);
        root.right.left.right = new TreeNode(2);

        System.out.println(root);

        Codec codec = new Codec();
        TreeNode deserialize;
        String s = codec.serialize(root);
        deserialize = codec.deserialize(s);
        System.out.println(deserialize.toString());
    }
}
