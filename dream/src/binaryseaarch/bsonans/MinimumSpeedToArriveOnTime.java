package binaryseaarch.bsonans;

import java.util.Arrays;

public class MinimumSpeedToArriveOnTime {
    public static void main(String[] args) {
//        System.out.println(minSpeedOnTime(new int[]{1,3,2},6));
//        System.out.println(minSpeedOnTime(new int[]{1,3,2},2.7));
//        System.out.println(minSpeedOnTime(new int[]{1,3,2},1.9));
        System.out.println(minSpeedOnTime(new int[]{1,1,100000},2.01));
    }
    public static int minSpeedOnTime(int[] dist, double hour) {
        int start=0,end = 10000000;
        int ans = -1;
        while(start <= end){
            int mid = start + (end - start)/2;
            System.out.println("mid : "+mid);
            if(isPossible(dist,hour,mid)){
                ans = mid;
//                System.out.println("in if "+mid);
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }

    public static boolean isPossible(int []dist,double hour,double speed){
        double hr=0;
        for(int i=0;i<dist.length;i++){
            int dis = dist[i];
            if(i == dist.length-1){
                hr+=((double)dis/speed);
            }else {
                hr += Math.ceil((double)dis / speed);
            }
            if(hr > hour){
                System.out.println(hr+" "+hour);
                return false;
            }
        }
        return hr <= hour;
    }
}
