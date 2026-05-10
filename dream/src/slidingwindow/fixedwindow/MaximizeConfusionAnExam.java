package slidingwindow.fixedwindow;

public class MaximizeConfusionAnExam {

    public static void main(String[] args) {
        System.out.println(maxConsecutiveAnswers("TTFF",2));
        System.out.println(maxConsecutiveAnswers("TFFT",1));
        System.out.println(maxConsecutiveAnswers("TTFTTFTT",1));
    }


    public static int maxConsecutiveAnswers2(String s, int k) {
        int l = 0;
        int maxf = 0;
        int result = 0;
        int n = s.length();
        int[] count = new int[26];

        for (int r = 0; r < n; r++) {
            maxf = Math.max(maxf, ++count[s.charAt(r) - 'A']);

            while (r - l + 1 > maxf + k) {  // very important pattern very very important
                --count[s.charAt(l++) - 'A'];
            }

            result = Math.max(result, r - l + 1);
        }

        return result;
    }
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        int left=0;
        int tCnt=0,fCnt=0;
        int longest =0;
        for(int right =0;right<answerKey.length();right++){
            char c =  answerKey.charAt(right);
            tCnt += c == 'T' ? 1:0;
            fCnt += c == 'F' ? 1:0;
            int minFlip = Math.min(tCnt,fCnt);
            while(minFlip > k){
                char c1 =  answerKey.charAt(left);
                tCnt -= c1 == 'T' ? 1:0;
                fCnt -= c1 == 'F' ? 1:0;
                minFlip = Math.min(tCnt,fCnt);
                left++;
            }
            longest = Math.max(longest,right-left+1);
        }

        return longest;
    }

    public static int maxConsecutiveAnswers1(String answerKey, int k) {
        int tCount = helper(answerKey,k,'F');
        int fCount = helper(answerKey,k,'T');
        return Math.max(tCount,fCount);
    }

    public static int helper(String ansKey,int k,char c){
        int start=0,count=0,max=0;
        for(int end=0;end<ansKey.length();end++){
            if(ansKey.charAt(end) == c){
                count++;
            }
            while(count > k){
                if(ansKey.charAt(start) == c){
                    count--;
                }
                start++;
            }
            max = Math.max(max,end-start+1);
        }
        return max;
    }
}
