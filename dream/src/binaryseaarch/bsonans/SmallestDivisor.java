package binaryseaarch.bsonans;

public class SmallestDivisor {
    public int smallestDivisor(int[] nums, int threshold) {
        int ans=-1;
        int low = 1;
        int high = nums[0];
        for(int num:nums){
            high = Math.max(high,num);
        }

        while(low <= high){
            int mid = low + (high - low)/2;
            if(divArr(nums,mid,threshold)){
                ans = mid;
                high = mid-1;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }

    public boolean divArr(int arr[],int div,int threshold){
        int sum=0;
        for(int i:arr){
            sum += Math.ceil((1.0*i)/div);
        }
        return sum <= threshold;
    }
}
