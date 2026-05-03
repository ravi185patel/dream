package binaryseaarch.bsonans;

import java.util.Arrays;

public class CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10},5));
        System.out.println(shipWithinDays(new int[]{3,2,2,4,1,4},3));
    }
    public static int shipWithinDays(int[] weights, int days) {
        int start=Arrays.stream(weights).max().getAsInt(),end = Arrays.stream(weights).sum();
        while(start <= end){
            int mid = start + (end-start)/2;
            if(isPossible(weights,days,mid)){
                end = mid-1;
            }else{
                start= mid + 1;
            }
        }
        return start;
    }

    public static boolean isPossible(int []weights,int days,int mid){
        int countDay=1,sum=0;
        for(int weight:weights){
            if(weight + sum > mid){// very very important condition.
                sum = weight;
                countDay++;

                /*
                day 1: 1,2,3,4,5  -> 6 + 15 > 15
                day 2: 6,7  -> 8 + 13 > 15
                day 3: 8 -> 9 + 8 > 15
                day 4: 9 -> 10 + 9 > 15
                day 5: 10
                 */
            }else{
                sum+=weight;
            }
        }
        return countDay <= days;
    }
}
