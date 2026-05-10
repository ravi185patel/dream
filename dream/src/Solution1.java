import common.CommonUtil;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    public static void main(String[] args) {
//        System.out.println(firstStableIndex(new int[]{5,0,1,4},3));
//        System.out.println(firstStableIndex(new int[]{3,2,1},1));
//        System.out.println(firstStableIndex(new int[]{0},0));
//        System.out.println(firstStableIndex(new int[]{0,0},0));

//        CommonUtil.print(concatWithReverse(new int[]{1,2,3}));
//        CommonUtil.print(concatWithReverse(new int[]{1}));
//        CommonUtil.print(concatWithReverse(new int[]{1,1,1}));
        CommonUtil.print(countWordOccurrences(new String[]{"hello wor","ld hello"},new String[]{"hello","wolrd","war"}));
        CommonUtil.print(countWordOccurrences(new String[]{"a--b a-","-c"},new String[]{"hello","wolrd","war"}));
//        System.out.println(minArraySum(new int[]{3,6,2}));
//        System.out.println(minArraySum(new int[]{3,6,2}));
    }
    public static int firstStableIndex1(int[] nums, int k) {
        int diff=-1;
        int length = nums.length;
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<length;i++){

            int max =0;
            for(int j=0;j<=i;j++){
                max = Math.max(max,nums[j]);
            }
            int min = Integer.MAX_VALUE;
            for(int j=i;j<length;j++){
                min = Math.min(min,nums[j]);
            }
//            System.out.println(max+" "+min);
            if(max-min <= k){
                if(ans > i){
                    return i;
                }
            }

        }
        return -1;
    }

    public static int firstStableIndex(int[] nums, int k) {
        int diff=-1;
        int length = nums.length;
        int ans=Integer.MAX_VALUE;
        int max[]=new int[length];
        for(int i=0;i<length;i++){
            if(i==0) {
                max[i] = nums[i];
            }else{
                max[i] = Math.max(nums[i],max[i-1]);
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i=length-1;i>=0;i--){

            min = Math.min(min,nums[i]);
            diff = max[i]-min;

            if(diff <= k){
                if(ans > i){
                    ans = i;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1: ans;
    }

    public static int[] concatWithReverse(int[] nums) {
        int n = nums.length;
        int res[]=new int[n*2];
        for(int i=0;i<n;i++){
            res[i]=nums[i];
            res[n+i]=nums[n-i-1];
        }
        return res;
    }

    public static int[] countWordOccurrences(String[] chunks, String[] queries) {

        StringBuilder sb= new StringBuilder();
        boolean flag = false;
        char prev = ' ';
        for(String chunk:chunks){
            for(char c:chunk.toCharArray()){
                if(c=='-'){
                    flag=true;
                }else{
                    if(flag){
                        if(Character.isAlphabetic(prev) && Character.isAlphabetic(c)){
                            sb.append("-");
                        }
                    }
                    sb.append(c);
                    prev=c;
                    flag=false;
                }
            }
        }
        System.out.println(sb.toString());
        return null;
    }

    public static long minArraySum(int[] nums) {
        return helper(0,nums);
    }

    public static long helper(int index,int[]nums){
        int length = nums.length;
        int sum=0,min= Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            sum = Integer.MAX_VALUE;
            for(int j=0;j<nums.length;j++){
                if(i!=j && nums[i]%nums[j]==0){
                    sum = sum == Integer.MAX_VALUE ? 0:sum;
                    sum+=nums[j];
                }
            }
            min = Math.min(min,sum);
        }

        return min;

    }
}
