import java.util.ArrayList;
import java.util.List;

public class Solution3 {

    public static void main(String[] args) {
        System.out.println(findValidElements(new int[]{1,2,4,2,3,2}));
        System.out.println(findValidElements(new int[]{1,2,4,2,3,5}));
        System.out.println(findValidElements(new int[]{5,5,5,5}));
        System.out.println(findValidElements(new int[]{1}));
        System.out.println(findValidElements(new int[]{1,2,1,1}));
    }
    public static List<Integer> findValidElements(int[] nums) {

        List<Integer> res = new ArrayList<>();
        int size = nums.length;
        for(int i=0;i<size;i++){
            int ele = nums[i];
            boolean isLeft = true,isRight = true;
            for(int j=0;j<i;j++){
                if(ele <= nums[j]){
                    isLeft=false;
                    break;
                }
            }
            for(int j=i+1;j<size;j++){
                if(ele <= nums[j]){
                    isRight=false;
                    break;
                }
            }
//            System.out.println(ele+" "+isLeft+" "+isRight);
            if(i==0 || i == size-1 || (isLeft || isRight)){
                res.add(nums[i]);
            }
        }
        return res;
    }
}
