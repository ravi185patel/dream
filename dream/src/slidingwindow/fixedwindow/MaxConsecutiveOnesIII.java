package slidingwindow.fixedwindow;

public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0},2));
        System.out.println(longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1},3));
    }
    public static int longestOnes(int[] nums, int k) {
        int start=0,max=0,count=0;
        for(int end=0;end<nums.length;end++){
            if(nums[end] ==0 ){
                count++;
            }
            while(count > k){
                if(nums[start] ==0 ){
                    count--;
                }
                start++;
            }

            max = Math.max(max,end-start+1);
        }
        return max;
    }
}
