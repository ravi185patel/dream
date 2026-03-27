package graph.takeuforward;

import graph.common.CommonUtil;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceInBinaryMaze {
    public static void main(String[] args) {
//        System.out.println(shortestPathBinaryMatrix(new int[][]{
//                {0,1},{1,0}
//        }));
//        System.out.println(shortestPathBinaryMatrix(new int[][]{
//                {0,0,0},{1,1,0},{1,1,0}
//        }));
//        System.out.println(shortestPathBinaryMatrix(new int[][]{
//                {1,0,0},{1,1,0},{1,1,0}
//        }));
        System.out.println(shortestPathBinaryMatrix(new int[][]{
                {0,1,1,0,0,0},
                {0,1,0,1,1,0},
                {0,1,1,0,1,0},
                {0,0,0,1,1,0},
                {1,1,1,1,1,0},
                {1,1,1,1,1,0}
        }));
    }
    public static int shortestPathBinaryMatrix(int[][] grid) {
        return bfs(grid);
    }
    public static int bfs(int [][]grid){
        if(grid[0][0] == 1) return -1;
        int m=grid.length;
        int n= grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean visited[][]=new boolean[m][n];
        queue.add(new int[]{0,0,1});
        visited[0][0]=true;
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int nodeX =node[0];
            int nodeY =node[1];
            int step = node[2];
            if(nodeX == m-1 && nodeY == n-1){
                return step;
            }
            for(int dir[]: CommonUtil.eightDirs){
                int newX = nodeX+dir[0];
                int newY = nodeY+dir[1];
                if(newX >= 0 && newX < m && newY >=0 && newY < n && grid[newX][newY] == 0 && visited[newX][newY]==false){
                    visited[newX][newY]=true;
                    queue.add(new int[]{newX,newY,step+1});
                }
            }
        }

        return -1;
    }

    public static int dij(int [][]grid){
            return -1;
    }
}
