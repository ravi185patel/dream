package slidingwindow.fixedwindowmonoincordec;

import common.CommonUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class SlidingWindowMedian {
    public static void main(String[] args) {
//        CommonUtil.print(findMedianInKWindow(new int[]{1,3,-1,-3,5,6,7},3));
        CommonUtil.print(findMedianInKWindow(new int[]{1,3,-1,-3,5,6,7},4));
    }

    public static double[] findMedianInKWindow(int nums[],int k){
//        return helperBF(nums,k);
        return helperSw(nums,k);
    }

    public static double[] helperSw(int nums[],int k){
        PriorityQueue<Integer> left =
                new PriorityQueue<>(Collections.reverseOrder()); // max heap

        PriorityQueue<Integer> right =
                new PriorityQueue<>(); // min heap

        double[] ans = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {

            // Add new number
            if (left.isEmpty() || nums[i] <= left.peek()) {
                left.offer(nums[i]);
            } else {
                right.offer(nums[i]);
            }

            balance(left, right);

            // Remove outgoing number
            if (i >= k) {
                int remove = nums[i - k];

                if (remove <= left.peek()) {
                    left.remove(remove);
                } else {
                    right.remove(remove);
                }

                balance(left, right);
            }

            // Calculate median
            if (i >= k - 1) {

                if (k % 2 == 0) {
                    ans[i - k + 1] =
                            ((double) left.peek() + right.peek()) / 2.0;
                } else {
                    ans[i - k + 1] = left.peek();
                }
            }
        }

        return ans;
    }

    private static void balance(PriorityQueue<Integer> left,
                         PriorityQueue<Integer> right) {

        // left can have at most 1 extra element
        while (left.size() > right.size() + 1) {
            right.offer(left.poll());
        }

        while (left.size() < right.size()) {
            left.offer(right.poll());
        }
    }

    public static void removeOp(int k,int index,PriorityQueue<Integer>minPq,
                                PriorityQueue<Integer>maxPq){
        while(!maxPq.isEmpty() && maxPq.peek() < index+k){
            maxPq.poll();
        }
        maxPq.remove(index);
        arrange(minPq,maxPq);
    }

    public static void arrange(PriorityQueue<Integer> minPq,PriorityQueue<Integer> maxPq){
        if(maxPq.size() - minPq.size() > 1){
            minPq.add(maxPq.poll());
        }else{
            maxPq.add(minPq.poll());
        }
    }

    public static double[] helperBF(int nums[],int k){
        int length = nums.length;
        double[] ans =new double[length-k+1];
        for(int i=0;i<nums.length-k+1;i++){
            int arr[]= Arrays.copyOfRange(nums,i,i+k);
            Arrays.sort(arr);
            int len = arr.length;
            if(k%2 == 0){
                long medium = (arr[len/2]+arr[len/2-1])/2;
                ans[i]=medium;
            }else{
                ans[i]=arr[len/2];
            }
        }
        return ans;
    }

        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0;
        int rightSize = 0;

        public double[] medianSlidingWindow(int[] nums, int k) {

            double[] ans = new double[nums.length - k + 1];

            for (int i = 0; i < nums.length; i++) {

                add(nums[i]);

                if (i >= k) {
                    remove(nums[i - k]);
                }

                balance();

                if (i >= k - 1) {

                    if (k % 2 == 1) {
                        ans[i - k + 1] = left.lastKey();
                    } else {
                        ans[i - k + 1] =
                                ((double) left.lastKey() + right.firstKey()) / 2.0;
                    }
                }
            }

            return ans;
        }

        private void add(int num) {

            if (left.isEmpty() || num <= left.lastKey()) {
                left.put(num, left.getOrDefault(num, 0) + 1);
                leftSize++;
            } else {
                right.put(num, right.getOrDefault(num, 0) + 1);
                rightSize++;
            }

            balance();
        }

        private void remove(int num) {

            if (num <= left.lastKey()) {
                decrease(left, num);
                leftSize--;
            } else {
                decrease(right, num);
                rightSize--;
            }

            balance();
        }

        private void balance() {

            // left can have at most one extra element
            while (leftSize > rightSize + 1) {

                int x = left.lastKey();

                decrease(left, x);
                leftSize--;

                right.put(x, right.getOrDefault(x, 0) + 1);
                rightSize++;
            }

            while (leftSize < rightSize) {

                int x = right.firstKey();

                decrease(right, x);
                rightSize--;

                left.put(x, left.getOrDefault(x, 0) + 1);
                leftSize++;
            }
        }

        private void decrease(TreeMap<Integer, Integer> map, int key) {

            map.put(key, map.get(key) - 1);

            if (map.get(key) == 0) {
                map.remove(key);
            }
        }
}
