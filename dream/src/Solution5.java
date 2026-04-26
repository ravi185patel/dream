import java.util.*;

public class Solution5 {

    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{3,3,2,1}));
        System.out.println(minOperations(new int[]{5,1,2,3}));
    }

    public static Set<Character> set =new HashSet<>();
    public static long minOperations(int[] nums) {
        long res = 0;
        for(int i=1;i<nums.length;i++){
            if(nums[i-1] > nums[i]){
                res += nums[i-1]-nums[i];
            }
        }
        return res;
    }
}
