package main

import "fmt"

func main() {
	root := &TreeNode{
		Val: 1,
		Left: &TreeNode{
			Val: 5,
			Right: &TreeNode{
				Val:   4,
				Left:  &TreeNode{Val: 9},
				Right: &TreeNode{Val: 2},
			},
		},
		Right: &TreeNode{
			Val:   3,
			Left:  &TreeNode{Val: 10},
			Right: &TreeNode{Val: 6},
		},
	}
	for _, v := range []int{1, 5, 3, 4, 10, 6, 9, 2} {
		t := amountOfTime(root, v)
		fmt.Println(v, t)
	}

}

type NodeInfo struct {
	Node   *TreeNode
	Parent *TreeNode
}

func amountOfTime(root *TreeNode, start int) (totalTime int) {
	if root == nil || root.Left == nil && root.Right == nil {
		return
	}
	nodeMap := make([]*NodeInfo, 100_002)
	var dfs func(node, parent *TreeNode)
	//depth := 0
	var startNode *TreeNode
	dfs = func(node, parent *TreeNode) {
		if node == nil {
			return
		}
		if node.Val == start {
			startNode = node
		}
		nodeMap[node.Val] = &NodeInfo{node, parent}

		dfs(node.Left, node)
		dfs(node.Right, node)
		//fmt.Println(node.Val, nodeMap[node.Val])
		return
	}

	dfs(root, nil)
	visited := make([]bool, 100_002)
	// 再来一次dfs
	var graphdfs func(node *TreeNode, depth int)
	graphdfs = func(node *TreeNode, depth int) {
		if node == nil || visited[node.Val] {
			return
		}
		visited[node.Val] = true

		totalTime = max(totalTime, depth)
		p := nodeMap[node.Val].Parent
		if p != nil {
			graphdfs(nodeMap[p.Val].Node, depth+1)
		}

		graphdfs(node.Left, depth+1)
		graphdfs(node.Right, depth+1)

	}
	graphdfs(startNode, 0)
	return
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func max(a, b int) int {
	if b > a {
		return b
	}
	return a
}
