package leetcode;

import Common.Color;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import leetcode.p36.Solution;


import java.io.*;

public class BaseRunner  {
    
    public  static  Testcase[] readTestcases(String filepath) throws IOException {
        
        File file = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(file);
        
        JSON json = new JSONObject();
        
        return JSON.parseObject(fileInputStream,Testcase[].class);
    }
    
    public static void main(String[] args) throws IOException {
        String filepath = "src/leetcode/p36/input.json";
        Testcase[] testcases = readTestcases(filepath);
        for(Testcase tc :testcases){
            System.out.println("Testing:"+tc);
            Solution solution = new Solution();
            boolean validSudoku = solution.isValidSudoku(tc.input);
            if(validSudoku == tc.output){
                System.out.println(Color.GREEN+"Pass!"+ Color.RESET);
            }else{
                System.out.println(Color.RED+"Failed!!"+ Color.RESET);
                
            }
    
        }
    }
}

