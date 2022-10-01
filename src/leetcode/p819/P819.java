package leetcode.p819;

import java.util.Arrays;
import java.util.HashMap;

public class P819 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] fields = paragraph.split("[^\\w]+");
        Arrays.sort(banned);
        int maxCnt = 0;
        String mostCommon = "";

        HashMap<String, Integer> map = new HashMap<>();
        for (String field : fields) {
            int i = Arrays.binarySearch(banned, field);
            if (i >= 0) {
                continue;
            }
            String s = field.toLowerCase();
            Integer cnt = map.getOrDefault(s, 0);
            ++cnt;
            if (cnt > maxCnt) {
                maxCnt = cnt;
                mostCommon = s;
            }
            map.put(s, cnt);
        }

        return mostCommon;
    }
}