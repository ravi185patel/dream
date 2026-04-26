package recursion;

import java.util.*;

public class SudokuSolver {
    public static void main(String[] args) {
        System.out.println(
                solveSudoku(new char[][]{
                        {'9','5','7','.','1','3','.','8','4'},
                        {'4','8','3','.','5','7','1','.','6'},
                        {'.','1','2','.','4','9','5','3','7'},
                        {'1','7','.','3','.','4','9','.','2'},
                        {'5','.','4','9','7','.','3','6','.'},
                        {'3','.','9','5','.','8','7','.','1'},
                        {'8','4','5','7','9','.','6','1','3'},
                        {'.','9','1','.','3','6','.','7','5'},
                        {'7','.','6','1','8','5','4','.','9'}
                })
        );

        System.out.println(
                solveSudoku(new char[][]{
                        {'.','1','.'},
                        {'3','5','.'},
                        {'4','.','6'}
                })
        );
    }

    public static boolean solveSudoku(char board[][]) {
        int m= board.length;
        int n = board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == '.'){
                    for(char k='1';k<='9';k++){
                        if(isValid(i,j,board,k)){
                            board[i][j] = k;
                            if(solveSudoku(board)){
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isValid(int x,int y,char board[][],char k){
        int m = board.length;
        int n = board[0].length;
        for(int i=0;i<m;i++){
            if(board[i][y]==k){
                return false;
            }
        }
        for(int i=0;i<n;i++){
            if(board[x][i]==k){
                return false;
            }
        }

        int startRow = 3 * (x/3);
        int startCol = 3 * (y/3);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[startRow+i][startCol+j] ==k) return false;
            }
        }
        return true;
    }
}
