package datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(){this.val = 0;}
    public TreeNode(int x) {
        val = x;
    }
    public List<TreeNode> levelTravel(){
        // list 是用来返回的，不用空间优化
        ArrayList<TreeNode> list = new ArrayList<>();
        int start = 0;
        list.add(this);
        TreeNode node;
        while (start<list.size()){
            node = list.get(start);
            if (node.left!=null){
                list.add(node.left);
            }
            if (node.right!=null) {
                list.add(node.right);
            }
            ++start;
        }
        return list;
    }
    @Override
    public String toString() {
        List<TreeNode> treeNodes = this.levelTravel();
        return Arrays.toString(treeNodes.stream().map(treeNode -> treeNode.val).toArray());
        //return "TreeNode{" +
        //        "val=" + val +
        //        ", left=" + left +
        //        ", right=" + right +
        //        '}';
    }
}