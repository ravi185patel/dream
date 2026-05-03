package binaryseaarch.bsonans;

import java.util.Arrays;

public class MaxRunTimeOfNComputers {
    public static void main(String[] args) {
        System.out.println(maxRunTime(2,new int[]{3,3,3}));
        System.out.println(maxRunTime(2,new int[]{1,1,1,1}));
        System.out.println(maxRunTime(3,new int[]{10,10,3,5}));
    }
    public static long maxRunTime(int n, int[] batteries) {
        long start = 1;
        long end = Arrays.stream(batteries).asLongStream().sum() / n;

        while (start <= end) {
            long mid = start + (end - start) / 2;

            if (isPossible(n, batteries, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end; // looking for max (upper bound)
        // if looking for min return start (lower bound)
    }

    public static boolean isPossible(int n,int []batteries,long mid){ // main logic
        long total = 0;

        for (int battery : batteries) {
            total += Math.min(battery, mid);
            /*
            Each battery contributes at most mid
            Total usable energy = sum(min(battery[i], mid))
             */
        }

        return total >= (long) n * mid;
    }

    public static long maxRunTime1(int n, int[] batteries) { // need to understand
        // Get the sum of all extra batteries.
        Arrays.sort(batteries);
        long extra = 0;
        for (int i = 0; i < batteries.length - n; i++) {
            extra += batteries[i];
        }

        // live stands for the n largest batteries we chose for n computers.

        int[] live = Arrays.copyOfRange(batteries, batteries.length - n, batteries.length);

        // We increase the total running time using 'extra' by increasing
        // the running time of the computer with the smallest battery.
        for (int i = 0; i < n - 1; i++) {
            // If the target running time is between live[i] and live[i + 1].
            if (extra < (long)(i + 1) * (live[i + 1] - live[i])) {
                return live[i] + extra / (long)(i + 1);
            }

            // Reduce 'extra' by the total power used.
            extra -= (long)(i + 1) * (live[i + 1] - live[i]);
        }

        // If there is power left, we can increase the running time
        // of all computers.
        return live[n - 1] + extra / n;
    }
}
