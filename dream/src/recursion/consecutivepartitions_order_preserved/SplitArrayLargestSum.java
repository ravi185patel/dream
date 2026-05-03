package recursion.consecutivepartitions_order_preserved;

import java.util.Arrays;

/*
https://leetcode.com/problems/split-array-largest-sum/description/
 */
public class SplitArrayLargestSum {
    public static void main(String[] args) {
        System.out.println(splitArray(new int[]{7,2,5,10,8},2));
        System.out.println(splitArray(new int[]{1,2,3,4,5},2));
        System.out.println(splitArray(new int[]{1,4,4},3));
    }
    public static int splitArray(int[] nums, int k) {
        int start=Arrays.stream(nums).max().getAsInt()
                ,end=Arrays.stream(nums).sum();
        while(start <= end){
            int mid = start + (end - start)/2;
            if(isKPartitionPossible(mid,nums,k)){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return start;
    }

    public static boolean isKPartitionPossible(int mid,int nums[],int k){
        int sum=0,count=1;
        for(int i=0;i<nums.length;i++){
            if(sum+nums[i] > mid){
                count++;
                sum=nums[i];
            }else{
                sum+=nums[i];
            }
        }

        return count > k;
    }
}
