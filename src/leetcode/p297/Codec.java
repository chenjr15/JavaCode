package leetcode.p297;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // 层次遍历
        if (root == null){
            return "";
        }
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        int head = 0;
        TreeNode node ;
        // lastNoneNull 用来截断后面连续的多余null
        int lastNoneNull = 1;
        for(;head<queue.size();++head){
            // 获取队头
            node =  queue.get(head);
            if (node ==null){
                //queue.add(null);
                //queue.add(null);
                continue;
            }
            lastNoneNull = head+1;
            queue.add(node.left);
            queue.add(node.right);
        }
        
        // 开始序列化
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(root.val);
        for(head=1;head<lastNoneNull;++head){
            node =  queue.get(head);
            sb.append(',');
            if (node == null){
                sb.append('x');
            }else{
                sb.append(node.val);
            }
        }
        System.out.println("encoded:"+sb.toString());
        return sb.toString();
    }

    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        TreeNode root = null;
        System.out.println("decoding:"+data);
        
        if (data==null || data==""){
            return root;
        }
        String[] nodeStrings = data.split(",");
        TreeNode[] nodeRef = new TreeNode[nodeStrings.length];
        TreeNode node;
        TreeNode parent;
        int p = 0;
        root =  new TreeNode(Integer.parseInt(nodeStrings[0]));
        nodeRef[0] = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        for(int i=1;i<nodeStrings.length;i+=2){
            parent = queue.poll();
            // 左节点
            if (!"x".equals(nodeStrings[i])){
                node =  new TreeNode(Integer.parseInt(nodeStrings[i]));
                parent.left = node;
                queue.offer(node);
            }
            
            
            // 右节点
            if (!"x".equals(nodeStrings[i+1])){
                // 存在节点，
                node = new TreeNode(Integer.parseInt(nodeStrings[i+1]));
                parent.right = node;
                queue.offer(node);
                
            }
        }
        return root;
        
    }
}

