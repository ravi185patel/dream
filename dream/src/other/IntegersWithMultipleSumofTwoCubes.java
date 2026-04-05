package other;

import java.util.*;

public class IntegersWithMultipleSumofTwoCubes {
    public static void main(String[] args) {
        System.out.println(findGoodIntegers(4104));
        System.out.println(findGoodIntegers(578));
        System.out.println(findGoodIntegers(33281));
    }
    public static List<Integer> findGoodIntegers(int n) {
//        Map<Integer,Integer> map = new HashMap<>();
//        for(int i=1;i<=Math.cbrt(n);i++){
//            map.putIfAbsent(i,i*i*i);
//        }

        List<Integer> res = new ArrayList<>();
        Map<Integer,Integer> freqMap = new HashMap<>();
        int end = (int) Math.cbrt(n);;
        for(int i=1;i<=end;i++){
            for(int j=i+1;j<=end;j++){
                int target = i*i*i + j*j*j;
                if(target <= n){
                    freqMap.putIfAbsent(target, 0);
                    freqMap.put(target,freqMap.getOrDefault(target,0)+1);
                }else {
                    break;
                }

                int count = freqMap.get(target);
                if(count == 2){
                    res.add(target);
                }
            }
        }
//
//        for(int key:freqMap.keySet()){
//            if(freqMap.get(key) == 2){
//                res.add(key);
//            }
//        }
        Collections.sort(res);
        return res;
    }
}
