package slidingwindow.variablewindow;

import java.util.HashMap;
import java.util.Map;

public class CountNumberOfNiceSubarrays {
    public static void main(String[] args) {
        System.out.println(numberOfSubarrays(new int[]{1,1,2,1,1},3));
        System.out.println(numberOfSubarrays(new int[]{2,4,6},1));
        System.out.println(numberOfSubarrays(new int[]{2,2,2,1,2,2,1,2,2,2},2));
    }
    public static int numberOfSubarrays(int[] nums, int k) {
//        return numberOfSubarraysSw(nums,k);
//        return numberOfSubarraysPS(nums,k);
        return numberOfSubarrays1(nums,k);
    }

    public static int numberOfSubarrays1(int[] nums, int k) {
        Map<Integer,Integer> hmap = new HashMap<>();
        int oddCount=0,nos=0;
        hmap.put(0,1);
        for(int i=0;i<nums.length;i++){
            if(nums[i] % 2 != 0){
                oddCount++;
            }
            if(hmap.containsKey(oddCount-k)){
                nos+=hmap.get(oddCount-k);
            }
            hmap.put(oddCount,hmap.getOrDefault(oddCount,0)+1) ;
        }

        return nos;
    }
    public static int numberOfSubarraysPS(int nums[],int k){
        int n = nums.length;
        int temp[]=new int[n];
        for(int i=0;i<n;i++){
            temp[i] = nums[i]%2 == 0 ? 0:1;
        }
        int goal = k;
        int sum=0,count=0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int right =0;right<temp.length;right++) {
            sum += temp[right];
            int reminder = sum - goal;
            if (map.containsKey(reminder)) {
                count += map.get(reminder);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    public static int numberOfSubarraysSw(int[] nums, int k) {
        int n = nums.length;
        int temp[]=new int[n];
        for(int i=0;i<n;i++){
            temp[i] = nums[i]%2 == 0 ? 0:1;
        }
        int goal = k;
        int sum=0,count=0,length=nums.length;
        int left = 0,prefixZero=0;
        for(int right =0;right<length;right++){
            sum+=temp[right];
            while(left < right && (temp[left] == 0 || sum > goal)){
                if(temp[left] == 0){
                    prefixZero++;
                }else{
                    prefixZero=0;
                }
                sum-=temp[left];
                left++;
            }
            if(sum == goal){
                count = count + 1 + prefixZero;
            }
        }
        return count;
    }

}
