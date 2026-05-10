package slidingwindow.variablewindow;

import java.util.HashMap;
import java.util.Map;

public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        System.out.println(numSubarraysWithSum(new int[]{1,0,1,0,1},2));
        System.out.println(numSubarraysWithSum(new int[]{0,0,0,0,0},0));
        System.out.println(numSubarraysWithSumPrefixSum(new int[]{1,0,1,0,1},2));
        System.out.println(numSubarraysWithSumPrefixSum(new int[]{0,0,0,0,0},0));
    }
    public static int numSubarraysWithSum(int[] nums, int goal) {
        int sum=0,count=0,length=nums.length;
        int left = 0,prefixZero=0;
        for(int right =0;right<length;right++){
            sum+=nums[right];

            while(left < right && (nums[left] == 0 || sum > goal)){
                if(nums[left] == 0){
                    prefixZero++;
                }else{
                    prefixZero=0;
                }
                sum-=nums[left];
                left++;
            }

            if(sum == goal){
                count = count + 1 + prefixZero;
            }

        }
        return count;
    }

    public static int numSubarraysWithSumPrefixSum(int[] nums, int goal) {
        Map<Integer,Integer> map = new HashMap<>();
        int sum=0,count=0;
        map.put(0,1);
        for(int right =0;right<nums.length;right++){
            sum+=nums[right];
            int reminder = sum - goal;
            if(map.containsKey(reminder)){
                count+=map.get(reminder);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return count;
    }
}
