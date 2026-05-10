package slidingwindow.fixedwindow;

public class FixedWindowTemplate {
    public void fixedWindow(int nums[],int k){
        // provide k directly or you need to identify it

        int start=0;
        int n = nums.length;
        int sum=0;
        for(int end=0;end<n;end++){
            sum+=nums[end];
            if(end-start+1 == k){ // based on condition shrink fixed window
                sum-=nums[start];
                start++;
            }
        }
        // return max or min or avg
    }
}
