package graph.interviewsheets;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LastDayWhereYouCanStillCross {
    public static void main(String[] args) {
        System.out.println(latestDayToCross(3,3,
                new int[][]{
                        {1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}
                }));
        System.out.println(latestDayToCross(2,2,
                new int[][]{
                        {1,1},{1,2},{2,1},{2,2}
                }));
        
    }
    public static int latestDayToCross(int row,int col,int [][]cells){
//        return latestDayToCrossBs(row,col,cells);
        return latestDayToCrossBfs(row,col,cells);
    }
    public static int dirs[][]={{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int latestDayToCrossBfs(int row, int col, int[][] cells) {
        int grid[][]=new int[row][col];
        int lastDay=0;

        for(int i=0;i<cells.length;i++){
            int cell[]=cells[i];
            int cellX = cell[0]-1;
            int cellY = cell[1]-1;
            grid[cellX][cellY] = 1;
            boolean flag = isPossibleToReachTopToBottom(row,col,grid);
            if(!flag){
                return i;
            }
        }
        return -1;
    } // time limit

    public static int latestDayToCrossBs(int row, int col, int[][] cells) {
        int left = 1;
        int right = row * col;

        while (left < right) {
            int mid = right - (right - left) / 2;
            if (canCross(row, col, cells, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }


        public static boolean canCross(int row, int col, int[][] cells, int day) {
            int[][] grid = new int[row][col];
            Queue<int[]> queue = new LinkedList<>();

            for (int i = 0; i < day; i++) {
                grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
            }

            for (int i = 0; i < col; i++) {
                if (grid[0][i] == 0) {
                    queue.offer(new int[]{0, i});
                    grid[0][i] = -1;
                }
            }

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1];
                if (r == row - 1) {
                    return true;
                }

                for (int[] dir : dirs) {
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];
                    if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col && grid[newRow][newCol] == 0) {
                        grid[newRow][newCol] = -1;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }

            return false;
    }

    public static boolean isPossibleToReachTopToBottom(int row,int col,int grid[][]){

        Queue<int[]> queue = new LinkedList<>();
        for(int j=0;j<col;j++){
            if(grid[0][j] == 0){
                queue.add(new int[]{0,j});
            }
        }
        Set<String> set=new HashSet<>();
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int cell[] = queue.poll();
                int cellX = cell[0];
                int cellY = cell[1];
                if (cellX == row - 1) {
                    return true;
                }
                for (int dir[] : dirs) {
                    int newCellX = dir[0] + cellX;
                    int newCellY = dir[1] + cellY;
                    if (newCellX < 0 || newCellX >= row || newCellY < 0 || newCellY >= col
                            || grid[newCellX][newCellY] == 1 || set.contains(newCellX + "," + newCellY)) {
                        continue;
                    }
                    set.add(newCellX + "," + newCellY);
                    queue.add(new int[]{newCellX, newCellY});
                }
            }
        }
        return false;
    }
}
