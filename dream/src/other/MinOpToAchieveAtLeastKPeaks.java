package other;

import java.util.Arrays;

public class MinOpToAchieveAtLeastKPeaks {

    public int minOperations(int[] nums, int k) {
        int[] qorvenalid = nums;
        if (k == 0) return 0;
        int n = nums.length;
        if (k > n / 2) return -1;

        long[] cost = new long[n];
        for (int i = 0; i < n; i++) {
            long prev = nums[(i - 1 + n) % n];
            long next = nums[(i + 1) % n];
            long maxNeighbor = Math.max(prev, next);
            if (nums[i] > maxNeighbor) {
                cost[i] = 0;
            } else {
                cost[i] = maxNeighbor + 1 - nums[i];
            }
        }

        long ans1 = solve(cost, 1, n - 1, k, 0, 0);
        long ans2 = solve(cost, 2, n - 2, k, cost[0], 1);

        long ans = Math.min(ans1, ans2);
        return (int) ans;
    }

    private long solve(long[] cost, int start, int end, int k, long initialCost, int initialCount) {
        int needed = k - initialCount;
        if (needed == 0) return initialCost;
        if (needed < 0) return 0;

        int len = end - start + 1;
        if (len < needed * 2 - 1) return (long) 1e16;

        long[] dp2 = new long[needed + 1];
        long[] dp1 = new long[needed + 1];
        long[] dp0 = new long[needed + 1];

        Arrays.fill(dp2, (long) 1e16);
        Arrays.fill(dp1, (long) 1e16);

        dp2[0] = 0;
        dp1[0] = 0;

        if (len >= 1) {
            dp2[1] = cost[start];
        }

        if (len >= 2) {
            dp1[1] = Math.min(cost[start], cost[start + 1]);
        }

        if (len == 1) return dp2[needed] + initialCost;
        if (len == 2) return dp1[needed] + initialCost;

        for (int i = 2; i < len; i++) {
            dp0[0] = 0;
            for (int j = 1; j <= needed; j++) {
                dp0[j] = Math.min(dp1[j], dp2[j - 1] + cost[start + i]);
            }

            long[] temp = dp2;
            dp2 = dp1;
            dp1 = dp0;
            dp0 = temp;
        }

        return dp1[needed] + initialCost;
    }
}
