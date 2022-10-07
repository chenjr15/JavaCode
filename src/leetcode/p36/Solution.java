package leetcode.p36;

import java.util.Arrays;

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] num = new boolean[10];
        // 遍历行
        for (char[] line : board) {
            Arrays.fill(num, false);
            for (char c : line) {
                if (c=='.')continue;
                if (!num[c - '0']) {
                    num[c - '0'] = true;
                } else {
                    return false;
                }
            }
        }
        
        // 遍历列
        for (int i = 0 ; i < 9; i++) {
            Arrays.fill(num, false);
            for (int j = 0; j < 9; j++) {
                char c = board[j][i];
                if (c=='.')continue;
                if (!num[c - '0']) {
                    num[c - '0'] = true;
                } else {
                    return false;
                }
            }
        }
        
        // 遍历矩形
        for (int start_x = 0 ; start_x < 9; start_x+=3) {
         
            for (int start_y = 0; start_y < 9; start_y+=3) {
                Arrays.fill(num, false);
                for(int i =0;i<3;++i){
                    for(int j =0;j<3;++j){
                        // i,j 是矩形左上角
                        char c = board[start_y+j][start_x+i];
                        if (c=='.')continue;
                        if (!num[c - '0']) {
                            num[c - '0'] = true;
                        } else {
                            return false;
                        }
                    }
                }
                
            }
        }
        
        return true;
    }
}