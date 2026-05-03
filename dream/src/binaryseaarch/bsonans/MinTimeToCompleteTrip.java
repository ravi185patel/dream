package binaryseaarch.bsonans;

import java.util.Arrays;

public class MinTimeToCompleteTrip {
    public long minimumTime(int[] time, int totalTrips) {
        long low = 1;
        long high = (long) Arrays.stream(time).min().getAsInt() * totalTrips;
        long ans=0;
        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (canComplete(time, totalTrips, mid)) {
                ans = mid;
                high = mid-1;     // try smaller time
            } else {
                low = mid + 1;  // need more time
            }
        }
        return ans ;//low;
    }

    private static boolean canComplete(int[] time, int totalTrips, long T) {
        long trips = 0;

        for (int t : time) {
            trips += T / t;
            if (trips >= totalTrips) return true; // early stop
        }

        return false;
    }
}
