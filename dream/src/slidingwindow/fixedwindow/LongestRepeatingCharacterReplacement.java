package slidingwindow.fixedwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB",2));
        System.out.println(characterReplacement("AABABBA",1));
    }
    public static int characterReplacement(String s, int k) {
        // window size maxOcc + k
        int start=0,max=0,maxOcc=0;
        Map<Character,Integer> map = new HashMap<>();
        int freq[]=new int[26];
        for(int end=0;end < s.length();end++){
            char c= s.charAt(end);
            freq[c-'A']++;
            maxOcc = Math.max(maxOcc,freq[c-'A']);
//            map.put(c,map.getOrDefault(c,0)+1);
//            maxOcc = Math.max(maxOcc,map.get(c));
            while(end-start+1 > maxOcc+k){
                char sC=s.charAt(start);
                freq[sC-'A']--;
                maxOcc = Math.max(maxOcc,freq[sC-'A']);
//                map.put(sC,map.get(sC)-1);
//                maxOcc = Math.max(maxOcc,map.get(sC));
//                if(map.get(sC)==0){
//                    map.remove(sC);
//                }
                start++;
            }
            max = Math.max(max,end-start+1);
        }
        return max;
    }
}
