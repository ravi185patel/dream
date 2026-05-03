package recursion.equalsumkbuckets;

import common.CommonUtil;

import java.util.Arrays;

/*

here k can be 1 to n
Can ask for 1 or 2 or 3 subsets.

 */
public class PartitionKSubsetEqualSum {
    public static void main(String[] args) {
        System.out.println(partitionKSubSubsetEqualSum(
                new int[]{1,2,2,2,1,1},3
        ));
        System.out.println(partitionKSubSubsetEqualSum(
                new int[]{1,2,2,2,1,2},3
        ));
    }

    public static boolean partitionKSubSubsetEqualSum(int nums[],int k){
        int sum = Arrays.stream(nums).sum();
        int target = sum/k;
        if(sum%k != 0){
            return false;
        }
        Arrays.sort(nums);
        CommonUtil.reverse(nums);
        int bucket[]=new int[k];
        return helper(0,nums,bucket,target);
    }

    public static boolean helper(int index,int nums[],int bucket[],int target){
        if(index == nums.length){
            return isValid(bucket,target);
        }

        for(int i=0;i<bucket.length;i++){
            if(bucket[i]+nums[index] > target) continue;
            bucket[i]+=nums[index];
            if(helper(index+1,nums,bucket,target)){
                return true;
            }
            bucket[i]-=nums[index];
        }
        return false;
    }

    public static boolean isValid(int buckets[],int target){
        for(int bucket:buckets){
            if(bucket != target){
                return false;
            }
        }
        return true;
    }
}
