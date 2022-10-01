package main

import (
	"fmt"
	"strings"
)

func main() {
	dictionary := Constructor()
	stringList := []string{"hello", "hallo", "leetcode"}
	dictionary.BuildDict(stringList)
}

type TireNode struct {
	children [26]*TireNode
	strings  []string
	isEnd    bool
}

type MagicDictionary struct {
	forward  TireNode
	backward TireNode
	strList  []string
}

func Constructor() MagicDictionary {
	return MagicDictionary{}
}

func (md *MagicDictionary) BuildDict(dictionary []string) {
	for _, s := range dictionary {
		md.forward.Insert(s)
		fmt.Println(md.forward.String())
	}
}

func (md *MagicDictionary) Search(searchWord string) (ok bool) {

	return
}

func (t *TireNode) Insert(s string) {
	p := t
	for _, c := range s {
		p.strings = append(p.strings, s)

		next := p.children[c-'a']
		if next == nil {
			next = new(TireNode)
			p.children[c-'a'] = next
		}
		p = next

	}
	p.isEnd = true
}

func (t *TireNode) Search(s string) {
	p := t
	for _, c := range s {
		next := p.children[c-'a']
		if next == nil {
			break
		}
		p = next
	}

}

func (t *TireNode) String() string {
	var rows [][]byte

	var dfs func(p *TireNode, c byte, rowIdx, depth int) int
	dfs = func(p *TireNode, c byte, rowIdx, depth int) int {
		for len(rows) <= rowIdx {
			rows = append(rows, []byte{})
		}
		row := rows[rowIdx]
		if len(row) <= depth {
			row = append(row, make([]byte, depth-len(row)+1)...)
			rows[rowIdx] = row
		}

		row[depth] = c

		fmt.Printf("%d,%d,%c\n", rowIdx, depth, c)
		roffset := 1
		for i, child := range p.children {
			if child != nil {

				roffset += dfs(child, byte('a'+i), roffset+rowIdx, depth+1)
			}
		}
		return roffset
	}
	dfs(t, '#', 0, 0)
	builder := strings.Builder{}
	for _, row := range rows {
		for i, c := range row {
			if c == 0 {
				row[i] = ' '
			}
		}
		builder.Write(row)
		builder.WriteRune('\n')

	}
	return builder.String()
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * obj := Constructor();
 * obj.BuildDict(dictionary);
 * param_2 := obj.Search(searchWord);
 */
