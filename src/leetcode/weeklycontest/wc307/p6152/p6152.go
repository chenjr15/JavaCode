package main

import "fmt"

func main() {
	r := minNumberOfHours(5, 3, []int{1, 4}, []int{2, 5})
	fmt.Println(r)
}
func minNumberOfHours(myEng int, myExp int, energy []int, experience []int) (cost int) {

	for i, eng := range energy {
		exp := experience[i]
		if eng >= myEng {
			cost += eng - myEng + 1
			myEng = eng + 1
		}
		if myExp <= exp {
			cost += exp - myExp + 1
			myExp = exp + 1
		}
		myEng -= eng
		myExp += exp
		// 不能打，算经验
	}
	return
}
