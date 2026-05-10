package slidingwindow.fixedwindow;

public class NumberOfSubstringsContainingAllThreeCharacters {
    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("abca"));
        System.out.println(numberOfSubstrings("abcabc"));
        System.out.println(numberOfSubstrings("abc"));
        System.out.println(numberOfSubstrings("aaacb"));
    }
    public static int numberOfSubstrings(String s) {
//        return helper1(s);
//        return helper(s);
        return helper2(s);
    }

    public static int helper1(String s){
        int count=0,start=0;
        int freq[]={0,0,0};
        for(int end=0;end<s.length();end++){
            char c = s.charAt(end);
            freq[c-'a']++;
            while(freq[0] > 0 && freq[1] > 0 && freq[2] > 0){
                char sC = s.charAt(start);
                freq[sC-'a']--;
                start++;
            }
            count+=start;
        }
        return count;
    }

    public static int helper2(String s){
        int count=0,start=0;
        int length = s.length();
        int freq[]={0,0,0};
        for(int end=0;end<s.length();end++){
            char c = s.charAt(end);
            freq[c-'a']++;
            while(freq[0] > 0 && freq[1] > 0 && freq[2] > 0){
                char sC = s.charAt(start);
                freq[sC-'a']--;
                start++;
                count+=(length-end);
            }
        }
        return count;
    }

    public static int helper(String s){
        int count=0,start=0;
        int freq[]={-1,-1,-1};
        for(int end=0;end<s.length();end++){
            int index = s.charAt(end)-'a';
            freq[index] = end;
            count= count + 1 + Math.min(freq[0],Math.min(freq[1],freq[2]));
        }
        return count;
    }
}
