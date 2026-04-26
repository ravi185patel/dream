package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllPermutationOfArrayOrString {
    public static void main(String[] args) {
        System.out.println(allPermutation(new int[]{1,2,3}));
    }

    public static List<String> allPermutation(int arr[]){
        List<String> res = new ArrayList<>();
        helper(0,arr,res);
        return res;
    }

    public static void helper(int index, int arr[], List<String> res){
        if(index == arr.length) {
            res.add(Arrays.toString(arr));
            return;
        }
        for(int i=index;i<arr.length;i++){
            swap(index,i,arr);
            helper(index+1,arr,res);
            swap(index,i,arr);
        }
    }

    public static void swap(int i,int j,int arr[]){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
