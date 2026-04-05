package other;

import java.util.*;

/*
recursion
 */
public class MaxWallsDestroyedByRobots {
    public static void main(String[] args) {
//        System.out.println(
//                maxWalls(new int[]{4}
//                ,new int[]{3}
//                ,new int[]{1,10})
//        );
//
//        System.out.println(
//                maxWalls(new int[]{10,2}
//                        ,new int[]{5,1}
//                        ,new int[]{5,2,7})
//        );
        System.out.println(
                maxWalls(new int[]{1,2}
                        ,new int[]{100,1}
                        ,new int[]{10})
        );
    }
    public static int maxWalls(int[] robots, int[] distance, int[] walls) {
        int res=0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int max =0;
        Set<Integer> set = new HashSet<>();
        Set<Integer> robotSet = new HashSet<>();
        for(int robot:robots){
            robotSet.add(robot);
        }
        for(int wall:walls){
            set.add(wall);
        }
        for(int i=0;i<robots.length;i++){
            int center = robots[i];
            int stop = distance[i];
            int left = -1,right = -1;
            for(left = center;left >= 0 && left >= center-stop ;left--){
                if(left != center && robotSet.contains(left)){
                    continue;
                }else{
                    break;
                }
            }
            for(right = center;right <= center+stop;right++){
                if(right != center && robotSet.contains(right)){
                    continue;
                }
            }
            if(set.contains(left)){
                res++;
                set.remove(left);
            }
        }
        return res;
    }

}
