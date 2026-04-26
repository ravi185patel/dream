package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        int res[]={4,5,3,4,2,1};
        mergeSortDivided(res,0,res.length-1);
        System.out.println(Arrays.toString(res));
    }

    public static void mergeSortDivided(int arr[],int left,int right){
        if(left >= right) return;
        int mid = left + (right - left)/2;
        mergeSortDivided(arr,left,mid);
        mergeSortDivided(arr,mid+1,right);
        mergeSort(arr,left,mid,right);
    }

    public static void mergeSort(int arr[],int left,int mid,int right){
        int low = left;
        int high = mid + 1;
        List<Integer> lst = new ArrayList<>();
        while(low <= mid && high <= right){
            if(arr[low] <= arr[high]){
                lst.add(arr[low]);
                low++;
            }else{
                lst.add(arr[high]);
                high++;
            }
        }
        while(low <= mid){
            lst.add(arr[low]);
            low++;
        }
        while(high <= right){
            lst.add(arr[high]);
            high++;
        }

        for(int i=left;i<=right;i++){
            arr[i]= lst.get(i-left);
        }
    }
}
