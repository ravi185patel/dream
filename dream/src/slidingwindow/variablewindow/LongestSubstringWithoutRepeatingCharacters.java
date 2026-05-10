package slidingwindow.variablewindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
    public static int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map=new HashMap<>();
        int start=0,max=0;
        for(int end=0;end<s.length();end++){
            char c= s.charAt(end);
            map.put(c,map.getOrDefault(c,0)+1);
            while(map.get(c) > 1){
                char sC = s.charAt(start);
                map.put(sC,map.get(sC)-1);
                if(map.get(sC) == 0){
                    map.remove(sC);
                }
                ++start;
            }
            max = Math.max(max,end-start+1);
        }
        return max;
    }
}
