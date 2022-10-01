package main

import (
	"fmt"
	"unsafe"
)

func main() {
	strs := []string{
		"5736732",
		"444947137",
		"00009",
		"00001105",
		"0000",
		"1",
	}
	var s, r string
	for _, s = range strs {
		r = largestPalindromic(s)
		fmt.Println(s, r)
	}

}
func largestPalindromic(num string) string {

	nums := [10]int{}
	for _, c := range num {
		nums[c-'0']++
	}
	var builder Builder
	// 从大到小开始找, 先找超过两个的，放在两边
	for i := 9; i >= 0; i-- {
		cnt := nums[i]
		if cnt < 2 {
			continue
		}
		if i == 0 && builder.Len() == 0 {
			continue
		}
		// 扣掉数量
		nums[i] &= 1
		ch := '0' + i
		for j := 0; j < cnt/2; j++ {
			builder.WriteByte(byte(ch))
		}
	}
	//fmt.Println(builder.String(), nums)

	leftlen := builder.Len()
	// 找最大的单个数字放中间
	for i := 9; i >= 0; i-- {
		cnt := nums[i]
		if cnt != 0 {
			ch := '0' + i
			builder.WriteByte(byte(ch))
			break
		}
	}
	//fmt.Println(builder.String())
	// 复制左半边到右半边
	for i := 0; i < leftlen; i++ {
		builder.WriteByte(builder.get(leftlen - i - 1))
	}
	return builder.String()
}

type Builder []byte

func (b *Builder) get(i int) byte {
	return (*b)[i]
}
func (b *Builder) Len() int {
	return len(*b)
}

func (b *Builder) String() string {

	return *((*string)(unsafe.Pointer(b)))
}
func (b *Builder) StringCopy() string {

	return string(*b)
}
func (b *Builder) WriteByte(c byte) {
	*b = append(*b, c)
}
