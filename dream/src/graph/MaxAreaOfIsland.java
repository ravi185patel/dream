package graph;

import common.CommonUtil;
import graph.basic.UnionDisjointByRankOrWeight;

import java.util.LinkedList;
import java.util.Queue;

public class MaxAreaOfIsland {
    public static void main(String[] args) {
        System.out.println(maxAreaOfIsland(new int[][]{
                {0,1,1,0,1},{1,0,1,0,1},{0,1,1,0,1},{0,1,0,0,1}
        }));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean visited[][]=new boolean[m][n];
        int maxAreaOfIsland=0;
       /* for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    maxAreaOfIsland = Math.max(maxAreaOfIsland,
//                            dfs(i,j,grid,visited)
                            bfs(i,j,grid,visited)
//                            dsu(i,j,m,n,grid,visited,dsu)
                    );
                }
            }
        }*/
        maxAreaOfIsland = dsu(grid);
        return maxAreaOfIsland;
    }

    public static int dfs(int x,int y,int grid[][],boolean visited[][]){
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visited[x][y] || grid[x][y] == 0){
            return 0;
        }
        visited[x][y] = true;
        int count=1;
        for(int dir[]:CommonUtil.fourDirs){
            int newX = dir[0] + x;
            int newY = dir[1] + y;
            count += dfs(newX,newY,grid,visited);

        }
        return count;
    }

    public static int bfs(int x,int y,int grid[][],boolean visited[][]){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;
        int count=1;

        while(!queue.isEmpty()){
                int[] node = queue.poll();
                int rx = node[0];
                int ry = node[1];
                for(int dir[]:CommonUtil.fourDirs){
                    int newX = dir[0] + rx;
                    int newY = dir[1] + ry;
                    if(newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length || visited[newX][newY] || grid[newX][newY] == 0){
                        continue;
                    }
                    visited[newX][newY] = true;
                    count++;
                    queue.add(new int[]{newX,newY});
                }
        }
        return count;
    }

    public static int dsu(int grid[][]){
        int m = grid.length;
        int n = grid[0].length;
        int maxAreaOfIsland=0;
        UnionDisjointByRankOrWeight dsu = new UnionDisjointByRankOrWeight(m*n);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    for(int dir[]:CommonUtil.fourDirs){
                        int newX = dir[0] + i;
                        int newY = dir[1] + j;
                        if(newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length || grid[newX][newY] == 0){
                            continue;
                        }
//                        grid[newX][newY] = 0;
                        dsu.unionWeight(i*n+j,newX*n+newY);
//                        maxAreaOfIsland = Math.max(maxAreaOfIsland,dsu.getSize(i*n+j));
                    }
                }
            }
        }
        for(int i = 0; i < m * n; i++){
            maxAreaOfIsland = Math.max(maxAreaOfIsland, dsu.getSize(i));
        }
        return maxAreaOfIsland;
    }
}
