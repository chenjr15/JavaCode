package nowcoder.ali.subset;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t = sc.nextInt();
        Main main = new Main();
        for(int i = 0;i<t;++i){
            int n = sc.nextInt();
            int[] x = new int[n];
            for(int k=0;k<n;++k){
                x[k] = sc.nextInt();
            }
            int[] y = new int[n];
            for(int k=0;k<n;++k){
                y[k] = sc.nextInt();
            }
            int maxSubset = main.getMaxSubset(n,x, y);
            System.out.println(maxSubset);
        }
    }
    int maxLen = 0;
    int[] x;
    int[] y;
    int n ;
    public   int getMaxSubset(int n ,int[] x, int[] y){
        this.maxLen = 0;
        this.n = n;
        this.x = x;
        this.y = y;
        dfs(new boolean[n],0,0);
        return maxLen;
    }
    public void dfs(boolean[] path,int added ,int depth){
        if (added>maxLen){
            maxLen = added;
        }
        if(depth >= x.length || (x.length - depth + added )<= maxLen ){
            // 剪枝
            return ;
        }

        // 判断是否可以加入集合，如果可以加入就加加进dfs
        boolean canAdd = true;
        for(int i=0;i<depth;++i){
            if (!path[i]){
                continue;
            }
            if (!(x[i]>x[depth] && y[i]<y[depth] || x[i]>x[depth]&& y[i]>y[depth])){
                canAdd = false;
                break;
            }
        }
        if (canAdd){
            path[depth] = true;
            dfs(path,added+1,depth+1);
            path[depth] = false;
        }
        // dfs 不加入的情况
        dfs(path,added,depth+1);
    }

}

