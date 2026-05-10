package slidingwindow.fixedwindow;

public class MaximumSumDistinctSubarraysWithLengthK {
    public static void main(String[] args) {
        System.out.println(maximumSubarraySum(new int[]{1,5,4,2,9,9,9},3));
        System.out.println(maximumSubarraySum(new int[]{4,4,4},3));
    }
    public static long maximumSubarraySum(int[] nums, int k) {
        int length = nums.length;
        int start=0,sum=0,max=0;
        int freq[]=new int[1000001];
        for(int end=0;end<length;end++){

            int ele = nums[end];
            freq[ele]++;
            sum+=ele;

            while(freq[ele] > 1 || end-start+1 > k){
                int startEle = nums[start];
                freq[startEle]--;
                sum-=startEle;
                start++;
            }

            if(end-start+1 == k){
                max = Math.max(max,sum);
            }
        }

        return max;
    }
}
