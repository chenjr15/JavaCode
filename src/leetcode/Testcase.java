package leetcode;

import java.util.Arrays;

public class Testcase {
    char[][] input;
    boolean output;
    
    @Override
    public String toString() {
        StringBuilder in = new StringBuilder();
        for (char[] line : input) {
            in.append(Arrays.toString(line));
            in.append('\n');
        }
        return "Testcase{" +
                "input:\n" + in +
                "output: " + output +
                "\n}";
    }
}
