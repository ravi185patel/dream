import common.CommonUtil;

public class FindTheDegreeOfEachVertex {
    public static void main(String[] args) {
        CommonUtil.print(findDegrees(new int[][]{
                {0,1,1},{1,0,1},{1,1,0}
        }));
        CommonUtil.print(findDegrees(new int[][]{
                {0,1,0},{1,0,0},{0,0,0}
        }));
        CommonUtil.print(findDegrees(new int[][]{
                {0}
        }));
        CommonUtil.print(findDegrees(new int[][]{
                {0,1,1,1},
                {1,0,1,1}
        }));
    }

    public static int[] findDegrees(int[][] matrix) {
        int nodes = matrix.length;
        int res[]=new int[nodes];
        for(int i=0;i<matrix.length;i++){
            for(int j = 0; j < matrix[i].length;j++){
                if(i!=j && matrix[i][j] == 1){
                    res[i]++;
                }
            }
        }

        return res;
    }
}
