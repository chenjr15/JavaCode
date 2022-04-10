package acmcoder.meituan.backend2020;
import java.util.*;

public class P1{
    public static void main(String[] argv){
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();
        int[] maxpos = new int[26];

        for( int i = 0 ;i <line.length();++i){
            maxpos[line.charAt(i)-'A']= i;

        }
        // System.out.println(Arrays.toString(maxpos));
        int lastPos = -1;
        for( int i = 0 ;i <line.length();){
            int curend = maxpos[line.charAt(i)-'A'];
            // 查找i+1 到 p 之间有没有某个字符的位置在p之外的
            for(int j= i+1;j<curend;j++){
                // 遇到curend之外的就将其更新为cruend，扩展右边界
                int pos = maxpos[line.charAt(j) - 'A'];
                if (pos >curend){
                    curend = pos;
                }

            }
            System.out.print((curend-lastPos)+" ");
            lastPos = curend;
            i=curend+1;
        }
    }
}