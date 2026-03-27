package graph.takeuforward;

import graph.basic.UnionDisjointByRankOrWeight;
import common.CommonUtil;

import java.util.HashSet;
import java.util.Set;

public class MakingLargeIsland {
    public static void main(String[] args) {
        System.out.println(largestIsland(new int[][]{
                {1,0},{0,1}
        }));
        System.out.println(largestIsland(new int[][]{
                {1,1},{0,1}
        }));
        System.out.println(largestIsland(new int[][]{
                {1,1},{1,1}
        }));
    }

    public static int largestIsland(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        UnionDisjointByRankOrWeight ds = new UnionDisjointByRankOrWeight(m*n+1);
        int count=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    for(int dir[]: CommonUtil.fourDirs) {
                        int newR = i + dir[0];
                        int newC = j + dir[1];
                        if(isValid(newR,newC,m)
                                && grid[newR][newC] == 1) {

                            int nodeNo = i*n + j;
                            int adjNodeNo = newR*n + newC;
                            ds.unionWeight(nodeNo, adjNodeNo);
                        }
                    }
                }
            }
        }
        int largestIsland=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1) continue;;
                Set<Integer> maxArea = new HashSet<>();
                for(int dir[]:CommonUtil.fourDirs) {
                    int newR = i + dir[0];
                    int newC = j + dir[1];
                    if(isValid(newR,newC,m)
                            && grid[newR][newC] == 1) {
                        int adjNodeNo = newR*n + newC;
                        maxArea.add(ds.find(adjNodeNo));
                    }
                }
                int totalSize =0;
                for(int parent:maxArea){
                    totalSize += ds.size[parent];
                }
                largestIsland = Math.max(largestIsland,totalSize+1);
            }
        }
        for(int i=0;i<m*n;i++){
            largestIsland = Math.max(largestIsland,ds.size[ds.find(i)]);
        }
        return largestIsland;
    }
    private static boolean isValid(int newR,int newC,int n){
        return newR >= 0 && newC >=0 && newR < n && newC < n;
    }
}
