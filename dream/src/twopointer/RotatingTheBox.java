package twopointer;

import common.CommonUtil;

public class RotatingTheBox {
    public static void main(String[] args) {
        CommonUtil.print(rotateTheBox(new char[][]{
                {'#','.','#'}
        }));
    }

    public static char[][] rotateTheBox(char[][] boxGrid) {
        int row = boxGrid.length,col = boxGrid[0].length;

        char [][] res = new char[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[j][i] = boxGrid[row-1-i][j];
            }
        }
        for (int i = col - 1; i >= 0; i--) {
            for (int j = 0; j < row; j++) {
                if (res[i][j] == '#') {
                    int curRow = i;
                    while (curRow+1 < col && res[curRow+1][j] == '.') {
                        curRow++;
                    }
                    if (curRow != i) {
                        res[curRow][j] = '#';
                        res[i][j] = '.';
                    }
                }
            }
        }
        return res;
    }

    public static char[][] rotateTheBox1(char[][] boxGrid) {

        int row = boxGrid.length;
        int col = boxGrid[0].length;

        // Step 1: Rotate 90 degree clockwise
        char[][] res = new char[col][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[j][row - 1 - i] = boxGrid[i][j];
            }
        }

        // Step 2: Apply gravity
        for (int j = 0; j < row; j++) {

            int empty = col - 1;

            for (int i = col - 1; i >= 0; i--) {

                if (res[i][j] == '*') {
                    empty = i - 1;
                }

                else if (res[i][j] == '#') {

                    res[i][j] = '.';
                    res[empty][j] = '#';
                    empty--;
                }
            }
        }

        return res;
    }
}
