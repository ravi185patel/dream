package slidingwindow.fixedwindow;

import java.util.*;

public class RepeatedDNASequences {
    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences1("AAAAACCCCCAAAAACCCCCAAAAAGGGTTT"));
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCAAAAAGGGTTT"));
    }

    public static List<String> findRepeatedDnaSequences(String s)
    {
        if (s.length() < 10) return new ArrayList<>();

        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);  // 00
        map.put('C', 1);  // 01
        map.put('G', 2);  // 10
        map.put('T', 3);  // 11

        Set<Integer> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        int hash = 0;
        int mask = (1 << 20) - 1;  // 20 bits for 10 chars (2 bits each)

        for (int i = 0; i < s.length(); i++) {
            // Shift left 2 bits, add new char (2 bits), and mask to 20 bits
            hash = ((hash << 2) | map.get(s.charAt(i))) & mask;

            if (i >= 9) {
                if (seen.contains(hash)) {
                    repeated.add(s.substring(i - 9, i + 1));
                } else {
                    seen.add(hash);
                }
            }
        }

        return new ArrayList<>(repeated);
    }
    public static List<String> findRepeatedDnaSequences1(String s) {
        List<String> res = new ArrayList<String>();
        Set<String> resset = new HashSet<String>();
        if(s == null || s.length() <= 10){
            return res;
        }
        Set<String> set = new HashSet<String>();
        int len = s.length();
        for(int i = 0; i <= len - 10; i++){
            String sub = s.substring(i, i + 10);
            if(!set.add(sub)){
                resset.add(sub);
            }
        }
        System.out.println(set);
        res.addAll(resset);
        return res;
    }
}
