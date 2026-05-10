package slidingwindow.fixedwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
    public static void main(String[] args) {
        System.out.println(totalFruit(new int[]{1,2,1}));
        System.out.println(totalFruit(new int[]{1,2,3,2,2}));
        System.out.println(totalFruit(new int[]{2,2,2,2,1}));
    }

    public static int totalFruit(int[] fruits) {
        return totalFruitSw(fruits);
    }

    public static int totalFruitNormal(int[] fruits) {
        // Variables to track max window size
        int maxlen = 0;

        // Track last and second last fruit types
        int lastFruit = -1, secondLastFruit = -1;

        // Count of current window and streak of last fruit
        int currCount = 0, lastFruitStreak = 0;

        // Traverse through each fruit
        for (int fruit : fruits) {

            // If fruit matches last two, expand window
            if (fruit == lastFruit || fruit == secondLastFruit) {
                currCount++;
            } else {
                // Reset window size to streak + 1
                currCount = lastFruitStreak + 1;
            }

            // Update lastFruit streak and fruit types
            if (fruit == lastFruit) {
                lastFruitStreak++;
            } else {
                lastFruitStreak = 1;
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            // Update max window size
            maxlen = Math.max(maxlen, currCount);
        }

        return maxlen;
    }

    public static int totalFruitSw(int[] fruits) {
        int start=0,max=0;
        Map<Integer,Integer> baskets = new HashMap<>();
        for(int end=0;end<fruits.length;end++){
            baskets.put(fruits[end],baskets.getOrDefault(fruits[end],0)+1);
            while( start < end && baskets.size() > 2){
                baskets.put(fruits[start],baskets.getOrDefault(fruits[start],0)+1);
                if(baskets.get(fruits[start]) == 0){
                    baskets.remove(fruits[start]);
                }
                start++;
            }
            max = Math.max(max,end-start+1);
        }
        return max;
    }

    public static int totalFruitBS(int[] fruits) {
        int start=1,end= Arrays.stream(fruits).sum();

        while(start < end){
            int mid = start + (end -start)/2;
            if(isPossible(fruits,mid)){
                end = mid - 1;
            }else{
                start=mid+1;
            }
        }
        return start;
    }

    public static boolean isPossible(int fruits[],int cap){
        int baskets = 0;

        for (int x : fruits) {
            // ceil(x / cap)
            baskets += (x + cap - 1) / cap;
            if (baskets > 2) {
                return false;
            }
        }
        return true;
    }

}
