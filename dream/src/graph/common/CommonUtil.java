package graph.common;

import java.util.Arrays;

public class CommonUtil {

    public static int fourDirs[][]={{-1,0},{0,-1},{0,1},{1,0}};
    public static int eightDirs[][]={{-1,0},{0,-1},{0,1},{1,0},{1,-1},{-1,1},{1,1},{-1,-1}};
    public static void print(int arr[]){
        System.out.println(Arrays.toString(arr));
    }


}
