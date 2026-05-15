package slidingwindow.fixedwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow( "ADOBECODEBANC",  "ABC"));
        System.out.println(minWindow( "a",  "a"));
        System.out.println(minWindow( "a",  "aa"));
    }

    /*
       tMap -> char with count
       sMap -> char with count

       when character count matched so string formed.
       formed == t.length -> reduce it from sMap.

     */
    public static String minWindow(String s, String t) {
        int start=0,length=s.length(),tLength=t.length(),min = Integer.MAX_VALUE;
        if(length < tLength) return "";
        Map<Character,Integer> tMap = new HashMap<>();
        Map<Character,Integer> sMap = new HashMap<>();
        String res="";
        for(char c:t.toCharArray()){
            tMap.put(c,tMap.getOrDefault(c,0)+1);
        }
        int formed = 0;
        int required = tMap.size();
        int minLength = Integer.MAX_VALUE;
        int end=0;
        int minLeft=0;
        for(end=0;end<length;end++){
            char c= s.charAt(end);
            sMap.put(c,sMap.getOrDefault(c,0)+1);
            if(tMap.containsKey(c) && tMap.get(c).intValue() == sMap.get(c).intValue()){
                formed++;
            }
            while(start <= end && formed == required ){
                int currLength = end-start+1;
                if(currLength < minLength){
                    minLength = currLength;
                    minLeft = start;
                }
                c= s.charAt(start);
                sMap.put(c,sMap.getOrDefault(c,0)-1);
                if(tMap.containsKey(c) && tMap.get(c).intValue() > sMap.get(c).intValue()){
                    formed--;
                }
                start++;
            }
        }
        if(minLength == Integer.MAX_VALUE){
            return "";
        }else{
            return s.substring(minLeft,minLeft+minLength);
        }
    }
    
    public static boolean equals(int freq[],int map[],String t){
        for(char c:t.toCharArray()){
            int index = c-'A';
            if(freq[index] != map[index]){
                return false;
            }
        }
        return true;
    }

}
