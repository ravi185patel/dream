package binaryseaarch.bsonans;

import java.util.Arrays;

public class MagneticForceBetweenTwoBalls {
    public static void main(String[] args) {
        System.out.println(maxDistance(new int[]{1,2,3,4,7},3));
        System.out.println(maxDistance(new int[]{5,4,3,2,1,1000000000},2));
    }
    public static int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int length = position.length;
        int min = position[0];//Arrays.stream(position).min().getAsInt();
        int max = position[length-1];//Arrays.stream(position).max().getAsInt();
        int start=0,end = max-min;
        int ans=0;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(isPossible(position,m,mid)){
                ans = mid;
                start = mid + 1;
            }else{
                end = mid-1;
            }
        }
        return ans;
    }

    public static boolean isPossible(int position[],int m,int mid){
        int count=1;
        int start= position[0];
        for(int i=1;i<position.length;i++){
            if(position[i] - start >= mid){
                start = position[i];
                count++;
            }
            if(count >= m){
                return true;
            }
        }
        return count >= m;
    }


}
