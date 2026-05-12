package slidingwindow.fixedwindowmonoincordec;

import common.CommonUtil;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        CommonUtil.print(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
//        return helperBF(nums,k);
        return helperSW(nums,k);
    }

    public static int[] helperBF(int nums[],int k){
        int res[]= new int[nums.length-k+1];
        for(int i=0;i<nums.length-k+1;i++){
            int max = nums[i];
            for(int j=i+1; j < k+i && j < nums.length;j++){
                // System.out.println(max+" "+nums[j]+" "+j);
                max = Math.max(max,nums[j]);
            }
            res[i] = max;
        }

        return res;
    }

    public static int[] helperSW(int nums[],int k){
        Deque<Integer> queue = new ArrayDeque<>();
        int left =0;
        int length = nums.length;
        int res[] =new int[length-k+1];
        int index=0;

        for(int i=0;i<length;i++){

            while(!queue.isEmpty() && queue.getFirst() <= i-k){ // shrink window if it is > k why i-k => 6-3 = 3 and if first element is 2 then shrink
                queue.removeFirst();
            }

            while(!queue.isEmpty() && nums[i] >= nums[queue.getLast()]){
                queue.removeLast();
            }

            queue.addLast(i);

            if(i >= k - 1){
                res[index]=nums[queue.getFirst()];
                index++;
            }
        }

        return res;

    }
}
