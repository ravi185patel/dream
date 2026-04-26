import java.util.*;

public class Solution4 {

    public static void main(String[] args) {
        System.out.println(sortVowels("leetcode"));
        System.out.println(sortVowels("aeiaaioooa"));
        System.out.println(sortVowels("baeiou"));
    }

    public static Set<Character> set =new HashSet<>();
    public static String sortVowels(String s) {
        set.add('e');
        set.add('a');
        set.add('i');
        set.add('o');
        set.add('u');

        Map<Character,Integer> map = new LinkedHashMap<>();
        int freq[]=new int[26];
        char cArr[]=s.toCharArray();
        List<Integer> index= new ArrayList<>();

        for(int i=0;i<cArr.length;i++){
            if(set.contains(cArr[i])){
                index.add(i);
                freq[cArr[i]-'a']++;
                Character c = cArr[i];
                map.put(c,map.getOrDefault(c,0)+1);
            }
        }


        List<Map.Entry<Character, Integer> > list =
                new LinkedList<Map.Entry<Character, Integer> >(map.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<Character, Integer> temp = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        // put data from sorted list to hashmap
        int ind=0;
        for(char key: temp.keySet()) {
            int value = temp.getOrDefault(key,0);
            for(;ind<index.size();ind++) {
                int i = index.get(ind);
                if (value == 0) {
                    break;
                }
                cArr[i] = key;
                value--;
            }
        }
        return new String(cArr);
    }
}
