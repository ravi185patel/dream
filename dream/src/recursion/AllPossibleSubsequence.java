package recursion;

import java.util.ArrayList;
import java.util.List;

public class AllPossibleSubsequence {
    public static void main(String[] args) {
        System.out.println(getSubsequences("abc"));
        System.out.println(getSubsequences("abca"));
        System.out.println(getSubsequences("aabc"));
    }

    public static List<String> getSubsequences(String s) {
        List<String> res = new ArrayList<>();
        char charArr[]=s.toCharArray();
        helper(charArr,0,new StringBuilder(),res);
        return res;
    }

    public static void helper(char charArr[],int index,StringBuilder sb,List<String> res){

        if(index > charArr.length){
            return;
        }

        if(sb.length()>=1){
            res.add(sb.toString());
        }

        for(int i=index;i<charArr.length;i++){
            if(i > index && charArr[i] == charArr[i-1]) continue;
            sb.append(charArr[i]);
            helper(charArr,i+1,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
