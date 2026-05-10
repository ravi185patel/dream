package graph;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumJumpsReachEndViaPrimeTeleportation {
    public static void main(String[] args) {
//        System.out.println(minJumps(new int[]{1,2,4,6}));
//        System.out.println(minJumps(new int[]{2,3,4,7,9}));
        System.out.println(minJumps(new int[]{4,6,5,8}));
    }

    public static int minJumps(int[] nums) {
        return helper(nums);
    }

    public static int helper(int nums[]){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,1});
        int count=0;
        int n = nums.length;
        boolean visited[]=new boolean[n];
        visited[0]=true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int points[] = queue.poll();
                int point = points[0];
                int cnt = points[1];
                if(point == n-1){
                    return cnt;
                }

                if(isPrime(nums[point])){
                    int primeIndex = -1;
                    for (int j = point + 1; j < nums.length; j++) {
                        if (nums[j] % nums[point] == 0) {
                            primeIndex = j;
                        }
                    }
                    if (primeIndex != -1 && visited[primeIndex] ==false) {
                        queue.add(new int[]{primeIndex,cnt+1});
                        visited[primeIndex] = true;
                    }
                }else{
                    if(point-1 >= 0 && visited[point-1] ==false) {
                        visited[point-1] = true;
                        queue.add(new int[]{point-1,cnt+1});
                    }
                    if(point+1 < n && visited[point+1] ==false) {
                        visited[point+1] = true;
                        queue.add(new int[]{point+1,cnt+1});
                    }
                }
                /*int left = point-1 >= 0 ? nums[point-1]:-1;
                int right = point+1 < n ? nums[point+1]:-1;
                int no1 = left;
                int no2 = right;
                if(left != -1) {
                    if (isPrime(no1)) {
                        int primeIndex = -1;
                        for (int j = point + 1; j < nums.length; j++) {
                            if (nums[j] % nums[point] == 0) {
                                primeIndex = j;
                            }
                        }
                        if (primeIndex == -1) {
                            queue.add(new int[]{point-1,cnt+1});
                        } else {
                            queue.add(new int[]{primeIndex,cnt+1});
                        }
                    }else{
                        queue.add(new int[]{point-1,cnt+1});
                    }
                }
                if(right != -1){
                    if(isPrime(no2)){
                        int primeIndex=-1;
                        for(int j=point+1;j<nums.length;j++){
                            if(nums[j]%nums[point]== 0){
                                primeIndex=j;
                            }
                        }
                        if (primeIndex == -1) {
                            queue.add(new int[]{point+1,cnt+1});
                        } else {
                            queue.add(new int[]{primeIndex,cnt+1});
                        }
                    }else{
                        queue.add(new int[]{point+1,cnt+1});
                    }
                }
*/
            }
        }
        return count;
    }

    public static boolean isPrime(int n){
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }
}
