package slidingwindow.fixedwindow;

public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1}));
        System.out.println(findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}));
    }
    public static int findMaxConsecutiveOnes(int[] nums) {
        int ones=0,max=0;
        for(int i:nums){
            if(i==1){
                ones++;
            }else{
                ones=0;
            }
            max = Math.max(max,ones);
        }
        return max;
    }
}
