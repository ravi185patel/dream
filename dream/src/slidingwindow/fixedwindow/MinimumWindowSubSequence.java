package slidingwindow.fixedwindow;

import java.util.HashMap;
import java.util.Map;

/*
vimp : not substring but subsequence with order
 */
public class MinimumWindowSubSequence {
    public static void main(String[] args) {
//        System.out.println(minWindow( "ADOBECODEBANC",  "ABC"));
//        System.out.println(minWindow( "a",  "a"));
//        System.out.println(minWindow( "a",  "aa"));
//        System.out.println(minWindow( "abcdebdde",  "bde"));
        System.out.println(minWindow( "abcde",  "bde"));
    }

    public static String minWindow(String S, String T) {
        int minLength = Integer.MAX_VALUE;
        String answer = "";

        // Try every starting point
        for (int i = 0; i < S.length(); i++) {

            int sIndex = i;
            int tIndex = 0;

            // Match T inside S
            while (sIndex < S.length()) {

                if (S.charAt(sIndex) == T.charAt(tIndex)) {
                    tIndex++;
                }

                // Entire T matched
                if (tIndex == T.length()) {

                    String window = S.substring(i, sIndex + 1);

                    // Update minimum answer
                    if (window.length() < minLength) {
                        minLength = window.length();
                        answer = window;
                    }

                    break;
                }

                sIndex++;
            }
        }

        return answer;
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
