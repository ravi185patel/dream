package recursion.equalsumkbuckets;

public class TargetUsingPlusMinusSign {
    public static void main(String[] args) {
        System.out.println(findExpression(new int[]{1,1,1,1,1},3));
        System.out.println(findExpression(new int[]{1},1));
        System.out.println(findExpression(new int[]{1,1,1,1,1},6));
    }

    public static int findExpression(int nums[],int target){
        return helper(0,nums,target,0);
    }
    public static int helper(int index,int nums[],int target,int sum){
        if(index == nums.length){
            return target == sum ? 1:0;
        }

        /*
        why below condition not worked
        -> because you first add all which sum can be > target
           and then you start minus elements to achieve target
           if(target < sum){
             return 0;
           }
           no this solution won't be count
         */
        int minus = helper(index+1,nums,target,sum-nums[index]);
        int plus = helper(index+1,nums,target,sum+nums[index]);
        return minus + plus;
    }
}
