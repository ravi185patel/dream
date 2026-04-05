package other;

public class MinimumIncreaseToMaximizeSpecialIndices {
    public static void main(String[] args) {
        System.out.println(minIncrease(new int[]{1,2,2}));
        System.out.println(minIncrease(new int[]{2,3,1,3}));
        System.out.println(minIncrease(new int[]{5,2,1,4,3}));
    }
        public static long minIncrease(int[] nums) {
            int n = nums.length;

            // Precompute cost for every valid index
            long[] cost = new long[n];
            for (int i = 1; i < n - 1; i++) {
                long target = Math.max(nums[i - 1], nums[i + 1]) + 1L;
                cost[i] = Math.max(0L, target - nums[i]);
            }

            // If odd length → only one pattern (odd indices)
            if (n % 2 != 0) {
                long total = 0;
                for (int i = 1; i < n - 1; i += 2) {
                    total += cost[i];
                }
                return total;
            }

            // Even case → try shifting window
            int k = n / 2;

            long evenSum = 0;
            for (int i = 2; i < n - 1; i += 2) {
                evenSum += cost[i];
            }

            long min = evenSum;
            long oddSum = 0;

            for (int i = 1; i < n - 1; i += 2) {
                oddSum += cost[i];
                evenSum -= (i + 1 < n - 1) ? cost[i + 1] : 0;

                min = Math.min(min, oddSum + evenSum);
            }

            return min;
        }
    }
