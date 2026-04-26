import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterviewQ {
    public static void main(String[] args) {
        List<Integer> lst = Arrays.asList(1,2,3,4,5,6);
        int start=0,end = lst.size();
        int k=3;
        List<Integer> res=new ArrayList<>();
        int sum=0;
        for(int i=0;i<end;i++){
            sum+=lst.get(i);
            if(k >= i-start){
                res.add(sum);
            }
            if(k == i-start+1) {
                sum -= lst.get(start);
                start++;
            }
        }
        System.out.println(res);


    }
}
