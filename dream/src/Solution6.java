import java.util.HashSet;
import java.util.Set;

/*
Recursion
   ↓
Memoization (same states)
   ↓
State compression (remove lastIndex)
   ↓
DP (O(n²))
   ↓
Data structure optimization (O(n log n))
 */
public class Solution6 {

    public static void main(String[] args) {
        System.out.println(maxAlternatingSum(new int[]{5,4,2},2));
        System.out.println(maxAlternatingSum(new int[]{3,5,4,2,4},1));
        System.out.println(maxAlternatingSum(new int[]{5},1));
    }

    public static Set<Character> set =new HashSet<>();
    public static long maxAlternatingSum(int[] nums, int k) {
        int n = nums.length;

        long[] dpUp = new long[n];
        long[] dpDown = new long[n];

        // Base case: single element subsequence
        for (int i = 0; i < n; i++) {
            dpUp[i] = nums[i];
            dpDown[i] = nums[i];
        }

        // Required variable (midway in function)
        int[] bralvoteni = nums;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i - k; j++) {

                if (nums[j] < nums[i]) {
                    dpUp[i] = Math.max(dpUp[i], dpDown[j] + nums[i]);
                }
                else if (nums[j] > nums[i]) {
                    dpDown[i] = Math.max(dpDown[i], dpUp[j] + nums[i]);
                }
            }
        }

        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, Math.max(dpUp[i], dpDown[i]));
        }

        return ans;
    }
}
