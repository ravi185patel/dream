package recursion;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsPhoneNumber {
    public static void main(String[] args) {
        System.out.println(letterCombination("34"));
        System.out.println(letterCombination("3"));
    }
    private final static String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static List<String> letterCombination(String digit){
        List<String> res = new ArrayList<>();
        helper(0,digit,new StringBuilder(),res);
        return res;
    }

    public static void helper(int index,String digit,StringBuilder sb,List<String> res){
        if(index == digit.length()){
            res.add(sb.toString());
            return;
        }

        int no = digit.charAt(index)-'0';
        for(char c:map[no].toCharArray() ){
            sb.append(c);
            helper(index+1,digit,sb,res);
            sb.deleteCharAt(sb.length()-1);
        }
    }

}
