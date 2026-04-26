package recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public static void main(String[] args) {
        System.out.println(combinationSumIII(3,7));
        System.out.println(combinationSumIII(3,9));
    }

    public static List<List<Integer>> combinationSumIII(int K,int N) {

        List<List<Integer>> res = new ArrayList<>();
        helper(0,1,K,N,new ArrayList<>(),res);
        return res;
    }

    public static void helper(int index,int start,int K,int sum,List<Integer> path,List<List<Integer>>res){
        if(index == K){
//            System.out.println(sum+" "+path);
            if(sum == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for(int i=start;i<=9;i++){
            if(sum < i ) continue;
            path.add(i);
            helper(index+1,i+1,K,sum-i,path,res);
            path.remove(path.size()-1);
        }
    }

}
