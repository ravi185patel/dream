package binaryseaarch.bsonans;

import java.util.Arrays;

public class MinimizedMaxProdDistributedAnyStore {
    public static void main(String[] args) {
        System.out.println(minimizedMaximum(6,new int[]{11,6}));
        System.out.println(minimizedMaximum(7,new int[]{15,10,10}));
    }

    public static int minimizedMaximum(int n, int[] quantities) {
        int max = Arrays.stream(quantities).max().getAsInt();
        int start=1,end=max;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(isPossible(quantities,mid,n)){
                end = mid -1;
            }else{
                start = mid + 1;
            }
        }
        return start;
    }

    public static boolean isPossible(int []quantities,int mid,int n){
        int count=0;
        for(int quantity:quantities){
            count+=(quantity + mid - 1)/mid;
            if(count > n){
                return false;
            }
        }
        return true;
    }
}
