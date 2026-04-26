public class Solution1 {
    public static void main(String[] args) {
        System.out.println(firstStableIndex(new int[]{5,0,1,4},3));
        System.out.println(firstStableIndex(new int[]{3,2,1},1));
        System.out.println(firstStableIndex(new int[]{0},0));
        System.out.println(firstStableIndex(new int[]{0,0},0));
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
}
