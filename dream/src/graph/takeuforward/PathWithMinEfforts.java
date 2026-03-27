package graph.takeuforward;

import graph.common.CommonUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathWithMinEfforts {
    public static void main(String[] args) {
        System.out.println(minimumEffortPath(new int[][]{
                {1,2,2},
                {3,8,2},
                {5,3,5}
        }));
        System.out.println(minimumEffortPath(new int[][]{
                {1,2,3},{3,8,4},{5,3,5}
        }));
    }
    public static int minimumEffortPath(int[][] heights) {
//        return bfsPq(heights);

//        int lo = 0, hi = 1_000_000;
//        while (lo < hi) {
//            int effort = lo + (hi - lo) / 2;
//            if (isPossibleBfs(effort,heights)) {
//                hi = effort;
//            }else {
//                lo = effort + 1;
//            }
//        }
//        return lo;

        return pureBfs(heights);
    }

    public static boolean isPossibleBfs(int diff,int heights[][]){
        int m = heights.length;
        int n = heights[0].length;

        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{0,0});
        boolean visited[][]=new boolean[m][n];
        visited[0][0]=true;
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int nodeX=node[0];
            int nodeY = node[1];
            if(nodeX == m-1 && nodeY == n-1){
                return true;
            }
            for(int dir[]: CommonUtil.fourDirs){
                int newNodeX = dir[0]+nodeX;
                int newNodeY = dir[1]+nodeY;
                if(newNodeX >=0 && newNodeX < m && newNodeY >=0 && newNodeY < n
                 && Math.abs(heights[newNodeX][newNodeY]-heights[nodeX][nodeY]) <=diff && visited[newNodeX][newNodeY] ==false){
                        visited[newNodeX][newNodeY]=true;
                        queue.add(new int[]{newNodeX,newNodeY});
                }
            }
        }
        return false;
    }

    public static int pureBfs(int heights[][]){
        int m = heights.length;
        int n = heights[0].length;

        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{0,0,0});
        int distance[][]=new int[m][n];
        for(int row[]:distance){
            Arrays.fill(row,(int)1e9);
        }
        distance[0][0]=0;
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int nodeX=node[0];
            int nodeY = node[1];
            int nodeDiff = node[2];
            if(nodeX == m-1 && nodeY == n-1){
                return distance[nodeX][nodeY];
            }
            for(int dir[]: CommonUtil.fourDirs){
                int newNodeX = dir[0]+nodeX;
                int newNodeY = dir[1]+nodeY;
                if(newNodeX >=0 && newNodeX < m && newNodeY >=0 && newNodeY < n){
                    int dis = Math.max(Math.abs(heights[newNodeX][newNodeY]-heights[nodeX][nodeY]),nodeDiff);
                    if(dis < distance[newNodeX][newNodeY]){
                        distance[newNodeX][newNodeY] = dis;
                        queue.add(new int[]{newNodeX,newNodeY,dis});
                    }
                }
            }
        }
        return 0;
    }

    public static int bfsPq(int heights[][]){
        int m = heights.length;
        int n = heights[0].length;

        Queue<int[]> queue=new PriorityQueue<>((i, j) -> i[2]-j[2]);
        queue.add(new int[]{0,0,0});
        int distance[][]=new int[m][n];
        for(int row[]:distance){
            Arrays.fill(row,(int)1e9);
        }
        distance[0][0]=0;
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int nodeX=node[0];
            int nodeY = node[1];
            int nodeDiff = node[2];
            if(nodeX == m-1 && nodeY == n-1){
                return nodeDiff;
            }
            for(int dir[]: CommonUtil.fourDirs){
                int newNodeX = dir[0]+nodeX;
                int newNodeY = dir[1]+nodeY;
                if(newNodeX >=0 && newNodeX < m && newNodeY >=0 && newNodeY < n){
                    int dis = Math.max(Math.abs(heights[newNodeX][newNodeY]-heights[nodeX][nodeY]),nodeDiff);
                    if(dis < distance[newNodeX][newNodeY]){
                        distance[newNodeX][newNodeY] = dis;
                        queue.add(new int[]{newNodeX,newNodeY,dis});
                    }
                }
            }
        }
        return -1;
    }



}
