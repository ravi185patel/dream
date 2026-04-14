package graph;

import common.CommonUtil;

import java.util.LinkedList;
import java.util.Queue;

public class IslandsAndTreasure {
    public static void main(String[] args) {
        CommonUtil.print(islandsAndTreasure(new int[][]{
                {2147483647,-1,0,2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647}
        }));

        CommonUtil.print(islandsAndTreasure(new int[][]{
                {0,-1},
                {2147483647,2147483647}
        }));

    }

    public static int[][] islandsAndTreasure(int[][] grid) {
//        return bfs(grid);
        return dfs(grid);
    }

    public static int[][] dfs(int [][] grid){
        int m = grid.length;
        int n = grid[0].length;

        boolean visited[][]=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 0){
                    solve(i,j,m,n,grid,0);
                }
            }
        }
        return grid;
    }

    public static void solve(int x,int y,int m,int n, int grid[][],int dist){
        if( x < 0 || x >= m || y < 0 || y >= n || grid[x][y] < dist){
            return;
        }

        grid[x][y] = dist;
        int res = 2147483647;
        for(int dir[]: CommonUtil.fourDirs){
            int newX = x + dir[0];
            int newY = y + dir[1];
            solve(newX,newY,m,n,grid,dist+1);
        }
    }
    public static int[][] bfs(int[][] grid){
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;

        boolean visited[][]=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 0){
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int node[]=queue.poll();
                int x = node[0];
                int y = node[1];
                for(int dir[]: CommonUtil.fourDirs){
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    if(newX < 0 || newX >= m || newY < 0 || newY >= n
                     || visited[newX][newY] || grid[newX][newY] == -1){
                        continue;
                    }

                    visited[newX][newY]=true;
                    grid[newX][newY]= grid[x][y] + 1;
                    queue.add(new int[]{newX,newY});
                }
            }
        }

        return grid;
    }

}
