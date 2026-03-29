package graph.interviewsheets;

import common.CommonUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindSafestPathGrid {
    final static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        System.out.println(maximumSafenessFactor(Arrays.asList(
                Arrays.asList(0,0,1),
                Arrays.asList(0,0,0),
                Arrays.asList(0,0,0)
        )));
        System.out.println(maximumSafenessFactor(Arrays.asList(
                Arrays.asList(0,1,1),
                Arrays.asList(0,0,0),
                Arrays.asList(0,0,0)
        )));
    }
    public static int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int distance[][]=new int[n][n];
        Queue<int[]> queue = new LinkedList<>();
        for(int row=0;row<n;row++){
            for(int col=0;col<n;col++){
                if(grid.get(row).get(col) == 1){
                    queue.add(new int[]{row,col});
                    distance[row][col]=0;
                }else{
                    distance[row][col]=-1;
                }
            }
        }

        setSaferPathFromAllOneInGrid(distance,n,queue);

        int maxDiff=Integer.MIN_VALUE,minDiff=0;
        for(int row=0;row<n;row++){
            System.out.println(Arrays.toString(distance[row]));
            for(int col=0;col<n;col++){
                maxDiff = Math.max(maxDiff,distance[row][col]);
            }
        }

        int res = findMaxSafePoint(distance,n,maxDiff);

        return res;

    }

    public static int findMaxSafePoint(int distance[][],int n,int maxDiff){
        int start=0,end=maxDiff;
        int ans=0;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(isSafePath(distance,mid,n)){
                ans = mid;
                start= mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return ans;
    }

    public static boolean isSafePath(int distance[][],int mid,int n){
        // Check if the source and destination cells satisfy minimum safeness
        if (distance[0][0] < mid || distance[n - 1][n - 1] < mid) {
            return false;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        boolean  visited[][]=new boolean[n][n];
        visited[0][0]=true;
        while(!queue.isEmpty()){
            int node[]=queue.poll();
            int cellX=node[0];
            int cellY = node[1];
            if(cellX == n-1 && cellY==n-1){
                return true;
            }
            for(int dir[]: dirs){
                int newCellX = dir[0]+cellX;
                int newCellY = dir[1]+cellY;
                if(newCellX < 0 || newCellX >= n || newCellY < 0 || newCellY >= n
                        || visited[newCellX][newCellY] || distance[newCellX][newCellY] < mid){
                    continue;
                }
                visited[newCellX][newCellY] = true;
                queue.add(new int[]{newCellX,newCellY});
            }
        }
        return false;
    }

    public static void setSaferPathFromAllOneInGrid(int [][]distance,int n, Queue<int[]> queue){
        while(!queue.isEmpty()){
            int size= queue.size();
            for(int i=0;i<size;i++){
                int cell[]=queue.poll();
                int cellX = cell[0];
                int cellY = cell[1];
                for(int dir[]:dirs){
                    int newCellX = cellX + dir[0];
                    int newCellY = cellY + dir[1];
                    if(newCellX < 0 || newCellX >= n || newCellY < 0
                            || newCellY >= n || distance[newCellX][newCellY] != -1
                    ){
                        continue;
                    }
                    distance[newCellX][newCellY] = 1 + distance[cellX][cellY];
                    queue.add(new int[]{newCellX,newCellY});
                }
            }
        }
    }
}
