package recursion;

import java.util.*;

public class NQueen {
    public static void main(String[] args) {
        System.out.println(solveNQueen(4));
    }

    public static Set<List<String>> solveNQueen(int n){
        Set<List<String>> res = new HashSet<>();
        char[][] grid = new char[n][n];
        for(char[] row:grid){
            Arrays.fill(row,'.');
        }
        int upperDiagonal[] = new int[2*n+1];
        int lowerDiagonal[] = new int[2*n+1];
        int col[] = new int[2*n+1];
        int row[] = new int[2*n+1];
//        dfs(0,n,grid,res);
        dfs(0,n,grid,res,upperDiagonal,lowerDiagonal,col,row);
        return res;
    }

    public static void dfs(int col, int n, char grid[][]
            ,Set<List<String>> res,int upperDiagonal[],int lowerDiagonal[],
                           int cols[],int rows[]){
        if(col == n){
            List<String> temp = new ArrayList<>();
            for(char row[]:grid){
                temp.add(String.valueOf(row));
            }
            res.add(temp);
            return;
        }
        for(int row=0;row<n;row++){
            if(rows[row] == 0 && lowerDiagonal[row+col]==0
            && upperDiagonal[n-1+col-row] ==0){
                rows[row] = 1;
                lowerDiagonal[row + col] = 1;
                upperDiagonal[n - 1 + col - row] = 1;
                grid[row][col]='Q';
                dfs(col+1,n,grid,res);
                grid[row][col]='.';
                rows[row] = 0;
                lowerDiagonal[row + col] = 0;
                upperDiagonal[n - 1 + col - row] = 0;
            }
        }
    }
    public static void dfs(int col, int n, char grid[][],Set<List<String>> res){
        if(col == n){
            List<String> temp = new ArrayList<>();
            for(char row[]:grid){
                temp.add(String.valueOf(row));
            }
            res.add(temp);
            return;
        }
        for(int row=0;row<n;row++){
            if(isQueenSafe(row,col,n,grid)){
                grid[row][col]='Q';
                dfs(col+1,n,grid,res);
                grid[row][col]='.';
            }
        }
    }

    public static boolean isQueenSafe(int row,int col,int n,char grid[][]){
        for(int i=0;i<col;i++){
            if(grid[row][i]=='Q') return false;
        }
        for(int i=0;i<row;i++){
            if(grid[i][col]=='Q') return false;
        }
        int i=row-1,j=col-1;
        while(i >= 0 && j >= 0){
            if(grid[i][j] =='Q'){
                return false;
            }
            i--;
            j--;
        }
        i=row+1;
        j=col-1;
        while(i < n && j >= 0){
            if(grid[i][j] =='Q'){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
