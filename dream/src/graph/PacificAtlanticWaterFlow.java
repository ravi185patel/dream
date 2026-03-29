package graph;

import java.util.*;

public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        System.out.println(pacificAtlantic(new int[][]{
                {1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}
        }));

        System.out.println(pacificAtlantic(new int[][]{
                {1}
        }));
    }
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
//        return pacificAtlanticHigherToLower(heights);
        return pacificAtlanticLowerToHigher(heights);
    }
    public static int dirs[][]={{-1,0,},{0,-1},{1,0},{0,1}};

    // bfs approached i) higher to lower
    public static List<List<Integer>> pacificAtlanticHigherToLower(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<m;i++){

            for(int j=0;j<n;j++){
                boolean pacific = isWaterReachToOcen(m,n,i,j,heights,false);
                boolean atlantic = isWaterReachToOcen(m,n,i,j,heights,true);
                if(pacific && atlantic){
                    res.add(Arrays.asList(i,j));
                }
            }
        }

        return res;
    }
    public static boolean isWaterReachToOcen(int m,int n,int sr,int sc,int [][]heights,boolean ocen){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr,sc});
        boolean visited[][]=new boolean[m][n];
        visited[sr][sc]= true;
        boolean atlantic = false,pacific = false;
        while(!queue.isEmpty()){
            int[] land = queue.poll();
            int landX = land[0];
            int landY = land[1];
            if(validateOcen(ocen,landX,landY,m,n)){
                return true;
            }
            for(int dir[]:dirs){
                int newLandX = dir[0] + landX;
                int newLandY = dir[1] + landY;
                if(isValidLand(landX,landY,newLandX,newLandY,m,n,heights,visited)){
                    visited[newLandX][newLandY]=true;
                    queue.add(new int[]{newLandX,newLandY});
                }
            }
        }

        return false;
    }
    public static boolean validateOcen(boolean ocen,int x,int y,int m,int n){
        return ocen ? (y==0 || x==0) : (x == m-1 || y == n-1);
    }
    public static boolean isValidLand(int x,int y,int newX,int newY,int m,int n,int [][]heights,boolean visited[][]){
        return newX >= 0 && newY >=0 && newX < m && newY < n && heights[x][y] >= heights[newX][newY] && visited[newX][newY] == false;
    }

    // bfs approched ii) lower to higher

    public static List<List<Integer>> pacificAtlanticLowerToHigher(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<List<Integer>> res = new ArrayList<>();
        boolean atlanticVisited[][]=new boolean[m][n];
        boolean pacificVisited[][]=new boolean[m][n];
        Queue<int[]> atlanticQueue = new LinkedList<>();
        Queue<int[]> pacificQueue = new LinkedList<>();

        for(int row=0;row<m;row++){
            pacificQueue.add(new int[]{row,0});
            atlanticQueue.add(new int[]{row,n-1});
            pacificVisited[row][0]=true;
            atlanticVisited[row][n-1]=true;
        }

        for(int col=0;col<n;col++){
            pacificQueue.add(new int[]{0,col});
            atlanticQueue.add(new int[]{m-1,col});
            pacificVisited[0][col]=true;
            atlanticVisited[m-1][col]=true;
        }

        traverse(m,n,atlanticQueue,heights,atlanticVisited);
        traverse(m,n,pacificQueue,heights,pacificVisited);
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(atlanticVisited[i][j] && pacificVisited[i][j]){
                    res.add(Arrays.asList(i,j));
                }
            }
        }
        return res;
    }

    public static void traverse(int m,int n,Queue<int[]> queue,int heights[][],boolean visited[][]){
        bfsTraverse(m,n,queue,heights,visited);
    }

    public static void bfsTraverse(int m,int n,Queue<int[]> queue,int heights[][],boolean visited[][]){
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int point[] =  queue.poll();
                int px = point[0];
                int py = point[1];
                int ht = heights[px][py];
                // visited[px][py]  = true;
                for(int dir[]:dirs){
                    int newPx = dir[0] + px;
                    int newPy = dir[1] + py;
                    if( newPx < 0 || newPx >= m || newPy < 0 || newPy >= n
                            || visited[newPx][newPy]
                            || ht > heights[newPx][newPy]){
                        continue;
                    }

                    visited[newPx][newPy] = true;
                    queue.add(new int[]{newPx,newPy});
                }
            }
        }
    }

}
