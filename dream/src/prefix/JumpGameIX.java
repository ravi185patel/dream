package prefix;

import common.CommonUtil;

public class JumpGameIX {
    public static void main(String[] args) {
        CommonUtil.print(maxValue(new int[]{2,3,1}));
        CommonUtil.print(maxValue(new int[]{2,1,3}));
    }

    public static int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] maxb = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++){
            max = Math.max(nums[i], max);
            maxb[i] = max;
        }

        int[] mina = new int[n];
        int min = nums[n-1];
        for(int i = n-1; i >= 0; i--){
            min = Math.min(nums[i], min);
            mina[i] = min;
        }

        int[] res = new int[n];
        res[n-1] = maxb[n-1];
        for(int i = n-2; i >= 0; i--){
            res[i] = maxb[i];
            if(maxb[i] > mina[i+1]) res[i] = res[i+1];
        }
        return res;
    }
}
