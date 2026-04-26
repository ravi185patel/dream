package recursion;

import common.CommonUtil;

public class QuickSort {
    public static void main(String[] args) {
        int res[]={4,3,2,1};
        quickSort(res,0,res.length-1);
        CommonUtil.print(res);
    }

    public static void quickSort(int arr[],int low,int high){
        if(low > high) return;
        int pivot = partition(arr,low,high);
        quickSort(arr,low,pivot-1);
        quickSort(arr,pivot+1,high);
    }

    public static int partition(int arr[],int low,int high){
        int pivotElement = arr[high];
        int pIndex = low;

        /*
          0 1 2 3 4
          4 5 6 2 1
          check any value <= pivotal
          pIndex start from 0 to n
          i start from 0 to n
         */
        for(int i=low;i<high;i++){
            if(arr[i] <= pivotElement){
                int temp = arr[pIndex];
                arr[pIndex] = arr[i];
                arr[i]=temp;
                pIndex++;
            }
        }
        int temp = arr[pIndex];
        arr[pIndex] = arr[high];
        arr[high]=temp;
        return pIndex;
    }
}
