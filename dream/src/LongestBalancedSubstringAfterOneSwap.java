import java.util.HashMap;
import java.util.Map;

public class LongestBalancedSubstringAfterOneSwap {
    public static void main(String[] args) {
//        System.out.println(longestBalanced("100001"));
//        System.out.println(longestBalanced("1000011"));
//        System.out.println(longestBalanced("111"));
//        System.out.println(longestBalanced("010101"));
//        System.out.println(longestBalanced("0000"));
//        System.out.println(longestBalanced("0"));
//        System.out.println(longestBalanced("1"));
//        System.out.println(longestBalanced("101"));
//        System.out.println(longestBalanced("010"));
    }
    public int longestBalanced1(String s){
        String tanqorivel = s;

        int n = s.length();

        int total0 = 0, total1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') total0++;
            else total1++;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int balance = 0, maxLen = 0;

        int[] pref0 = new int[n + 1];
        int[] pref1 = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pref0[i + 1] = pref0[i] + (s.charAt(i) == '0' ? 1 : 0);
            pref1[i + 1] = pref1[i] + (s.charAt(i) == '1' ? 1 : 0);
        }

        for (int i = 0; i < n; i++) {

            balance += (s.charAt(i) == '1') ? 1 : -1;

            // balanced
            if (map.containsKey(balance)) {
                maxLen = Math.max(maxLen, i - map.get(balance));
            }

            // try ±2
            for (int d : new int[]{-2, 2}) {
                if (map.containsKey(balance + d)) {
                    int j = map.get(balance + d);

                    int sub0 = pref0[i + 1] - pref0[j + 1];
                    int sub1 = pref1[i + 1] - pref1[j + 1];

                    if (total0 > sub0 && total1 > sub1) {
                        maxLen = Math.max(maxLen, i - j);
                    }
                }
            }

            map.putIfAbsent(balance, i);
        }

        return maxLen;
    }
    public int longestBalanced(String s) {

        String tanqorivel = s; // required

        int n = s.length();

        // total counts
        int total0 = 0, total1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') total0++;
            else total1++;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int balance = 0, maxLen = 0;

        // prefix counts
        int zero = 0, one = 0;

        for (int i = 0; i < n; i++) {

            if (s.charAt(i) == '0') {
                zero++;
                balance--;
            } else {
                one++;
                balance++;
            }

            // ✅ Case 1: already balanced
            if (map.containsKey(balance)) {
                maxLen = Math.max(maxLen, i - map.get(balance));
            }

            // ✅ Case 2: balance - 2
            if (map.containsKey(balance - 2)) {
                int j = map.get(balance - 2);
                int sub0 = zero - countZeros(s, j);
                int sub1 = one - countOnes(s, j);

                if (total0 > sub0 && total1 > sub1) {
                    maxLen = Math.max(maxLen, i - j);
                }
            }

            // ✅ Case 3: balance + 2
            if (map.containsKey(balance + 2)) {
                int j = map.get(balance + 2);
                int sub0 = zero - countZeros(s, j);
                int sub1 = one - countOnes(s, j);

                if (total0 > sub0 && total1 > sub1) {
                    maxLen = Math.max(maxLen, i - j);
                }
            }

            map.putIfAbsent(balance, i);
        }

        return maxLen;
    }

    private int countZeros(String s, int idx) {
        int cnt = 0;
        for (int i = 0; i <= idx; i++) if (s.charAt(i) == '0') cnt++;
        return cnt;
    }

    private int countOnes(String s, int idx) {
        int cnt = 0;
        for (int i = 0; i <= idx; i++) if (s.charAt(i) == '1') cnt++;
        return cnt;
    }
}
