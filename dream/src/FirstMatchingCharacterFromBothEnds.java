public class FirstMatchingCharacterFromBothEnds {

    public static void main(String[] args) {
        System.out.println(firstMatchingIndex("abcacbd"));
        System.out.println(firstMatchingIndex("abc"));
        System.out.println(firstMatchingIndex("abcdab"));
        System.out.println(firstMatchingIndex("aaaabbbbbaaaa"));
    }
    public static int firstMatchingIndex(String s) {
        int n = s.length();
        if(n == 0) return -1;
        for(int i=0;i<=n/2;i++){
            if(s.charAt(i) == s.charAt(n-1-i)){
                return i;
            }
        }
        return -1;
    }
}
