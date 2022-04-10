package leetcode.p796;

public class P796 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.rotateString("ohbrwzxvxe", "uornhegseo");
        System.out.println(b);
    }
}

class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int n = s.length();
        // 双指针
        // 找到s中和goal第一个字符串相匹配的字符

        boolean allMach = false;
        for (int pa = 0; pa < n; pa++) {

            if (s.charAt(pa) == goal.charAt(0)) {
                // 开始尝试从pa 匹配，看是否能够全部相等
                allMach = true;
                for (int i = 0; i < n; ++i) {
                    if (s.charAt((pa + i) % n) != goal.charAt(i)) {
                        // 发现不相等
                        allMach = false;
                        break;
                    }
                }
                if (allMach) {

                    break;
                }
            }
        }
        return allMach;

    }
}