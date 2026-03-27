package graph.takeuforward;

import graph.common.CommonUtil;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SwimInRisingWater {
    public static void main(String[] args) {
        System.out.println(swimInWater(new int[][]{
                {0,2},{1,3}
        }));
        System.out.println(swimInWater(new int[][]{
                {0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}
        }));
    }
    public static int swimInWater(int[][] grid) {
        Queue<int[]> queue = new PriorityQueue<>((i,j) ->i[2]-j[2]);
        queue.add(new int[]{0,0,grid[0][0]});
        int m = grid.length;
        int n = grid[0].length;
        boolean visited[][]=new boolean[m][n];
        visited[0][0]=true;
        int count=0;
        while(!queue.isEmpty()){
            int cell[]=queue.poll();
            if(cell[0]==m-1 && cell[1]==n-1){
                return cell[2];
            }
            count++;
            for(int dir[]: CommonUtil.fourDirs){
                int newX = cell[0] + dir[0];
                int newY = cell[1] + dir[1];
                if(newX >= m || newX < 0 || newY >= n || newY < 0 || visited[newX][newY]){
                    continue;
                }
                int diff = Math.max(grid[newX][newY],cell[2]);
                visited[newX][newY]=true;
                queue.add(new int[]{newX,newY,diff});
            }
        }
        return -1;
    }
}
