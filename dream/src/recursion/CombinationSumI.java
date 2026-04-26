package recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumI {
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,6,7},7));
    }

    public static List<List<Integer>> combinationSum(int arr[],int sum) {

        List<List<Integer>> res = new ArrayList<>();
//        helper(arr,0,sum,new ArrayList<>(),res);
        helper1(arr,0,sum,new ArrayList<>(),res);
        return res;
    }

    public static void helper(int arr[],int index,int sum,List<Integer> lst,
                              List<List<Integer>> res){

        if(sum == 0){
            res.add(new ArrayList<>(lst));
            return;
        }
        if(index == arr.length){
            return;
        }
        for(int i=index;i<arr.length;i++){
            if(sum < arr[i]) continue;
            lst.add(arr[i]);
            helper(arr,i,sum-arr[i],lst,res);
            // for index 0 -> 0 -> 0 until sum == 0 or sum < 0
            // index 0 -> 1 -> 1 -> 1 -> 1 until sum =0 or sum < 0
            lst.remove(lst.size()-1);
        }
    }

    public static void helper1(int arr[],int index,int sum,List<Integer> lst,
                              List<List<Integer>> res){

        if(sum == 0){
            res.add(new ArrayList<>(lst));
            return;
        }
        if(sum >= arr[index]) {
            lst.add(arr[index]);
            helper(arr, index, sum - arr[index], lst, res);
            // for index 0 -> 0 -> 0 until sum == 0 or sum < 0
            // index 0 -> 1 -> 1 -> 1 -> 1 until sum =0 or sum < 0
            lst.remove(lst.size() - 1);
        }
        helper(arr,index+1,sum,lst,res);
    }
}
