package other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MirrorFrequencyDistance {
    public static void main(String[] args) {
        System.out.println(mirrorFrequency("ab1z9"));
        System.out.println(mirrorFrequency("4m7n"));
        System.out.println(mirrorFrequency("byby"));
        System.out.println(mirrorFrequency("xyza"));
        System.out.println(mirrorFrequency("abcx"));
        System.out.println(mirrorFrequency("xyz"));
        System.out.println(mirrorFrequency("999"));
        System.out.println(mirrorFrequency("999123"));
        System.out.println(mirrorFrequency("989013"));
    }
    public static int mirrorFrequency(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for(char c:s.toCharArray()){
            map.putIfAbsent(c,0);
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int sumFreq=0;
        Set<Character> set = new HashSet<>();
        for(char c:map.keySet()){
            if(!set.contains(c)){
                if(Character.isAlphabetic(c)) {
                    char other = (char) ('z' - (c - 'a'));
                    sumFreq += Math.abs(map.getOrDefault(c, 0) - map.getOrDefault(other,0));
                    set.add(c);
                    set.add(other);
                }else{
                    char other = (char) ('9' - (c - '0'));
                    sumFreq += Math.abs(map.getOrDefault(c, 0) - map.getOrDefault(other,0));
                    set.add(c);
                    set.add(other);
                }
            }
        }
        return sumFreq;
    }

    public static int mirrorFrequency1(String s) {
        int[] freq = new int[128];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += Math.abs(freq['0' + i] - freq['9' - i]);
        }
        for (int i = 0; i < 13; i++) {
            sum += Math.abs(freq['a' + i] - freq['z' - i]);
        }

        return sum;
    }
}
