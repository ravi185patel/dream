package graph.takeuforward;

import java.util.Arrays;

public class FindCityWithMinNeighborsAtThresholdDis {
    public static void main(String[] args) {
        System.out.println(findTheCity(5,new int[][]{
                {0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}
        },2));
    }
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int dp[][]=new int[n][n];
        for(int row[]:dp){
            Arrays.fill(row,Integer.MAX_VALUE);
        }
        for(int edge[]:edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            dp[u][v] = wt;
            dp[v][u] = wt;

        }
        for(int i=0;i<n;i++) {
            dp[i][i]=0;
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dp[i][k] == Integer.MAX_VALUE || dp[k][j] == Integer.MAX_VALUE) continue;
                    dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k][j]);
                }
            }
        }

        int city=-1,cntCity=n;
        for(int i=0;i<n;i++){
            int count=0;
            for(int j=0;j<n;j++){
                if(dp[i][j] == distanceThreshold){
                    count++;
                }
            }
            if(count < cntCity){
                cntCity=count;
                city = i;
            }
        }
        return city;
    }
}
