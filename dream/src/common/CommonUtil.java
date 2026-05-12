package common;

import java.util.Arrays;

public class CommonUtil {

    public static int fourDirs[][]={{-1,0},{0,-1},{0,1},{1,0}};
    public static int eightDirs[][]={{-1,0},{0,-1},{0,1},{1,0},{1,-1},{-1,1},{1,1},{-1,-1}};
    public static void print(int arr[]){
        System.out.println(Arrays.toString(arr));
    }
    public static void print(long arr[]){
        System.out.println(Arrays.toString(arr));
    }

    public static void print(double arr[]){
        System.out.println(Arrays.toString(arr));
    }
    public static void print(char arr[]){
        System.out.println(Arrays.toString(arr));
    }

    public static void print(int arr[][]){
        for(int row[]:arr){
           print(row);
        }
    }

    public static void print(long arr[][]){
        for(long row[]:arr){
            print(row);
        }
    }
    public static void print(char arr[][]){
        for(char row[]:arr){
            print(row);
        }
    }

    public static void reverse(int nums[]){
        for(int i=0;i<nums.length/2;i++){
            int temp = nums[i];
            nums[i] = nums[nums.length-1-i];
            nums[nums.length-1-i]=temp;
        }
    }
}
