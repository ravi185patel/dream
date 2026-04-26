package recursion;

import java.util.ArrayList;
import java.util.List;

public class PelindromePartition {
    public static void main(String[] args) {
        System.out.println(pelindromePartition("aab"));
        System.out.println(pelindromePartition("baa"));
        System.out.println(pelindromePartition("aabaa"));
    }

    public static List<List<String>> pelindromePartition(String str){
        List<List<String>> res = new ArrayList<>();
        helper(0,str,new ArrayList<>(),res);
        return res;
    }

    public static void helper(int index,String str,List<String> path,List<List<String>> res){
        if(index == str.length()){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=index;i<str.length();i++){
            String pel = str.substring(index,i+1);
            if(isPelin(pel)){
                path.add(pel);
                helper(i+1,str,path,res);
                path.remove(path.size()-1);
            }
        }
    }

    public static boolean isPelin(String pel){
        int start=0,end=pel.length()-1;
        while(start < end){
            if(pel.charAt(start) != pel.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
