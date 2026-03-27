package graph.takeuforward;

import java.util.*;

public class NetworkDelayTime {
    public static void main(String[] args) {
        System.out.println(networkDelayTime(
                new int[][]{
                        {2,1,1},{2,3,1},{3,4,1}
                },4,2
        ));
        System.out.println(networkDelayTime(
                new int[][]{
                        {1,2,1}
                },2,1
        ));
        System.out.println(networkDelayTime(
                new int[][]{
                        {1,2,1}
                },2,2
        )); 
        System.out.println(networkDelayTime(
                new int[][]{
                        {1,2,1},{1,3,4},{2,3,2},{4,3,1}
                },4,1
        ));
    }
    public static int networkDelayTime(int[][] times, int n, int k) {
        return dijks(times,n,k);
    }

    public static int dijks(int[][]times,int n,int k){
        Map<Integer,List<int[]>> graphMap =  createGraph(times,n);
        Queue<int[]> queue = new PriorityQueue<>((i,j) -> i[1]-j[1]);
        queue.add(new int[]{k,0});
        int distance[]=new int[n+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[k]=0;
        while(!queue.isEmpty()){
            int node[]=queue.poll();
            int x = node[0];
            for(int nbNode[]:graphMap.getOrDefault(x,new ArrayList<>())){
                int dist = nbNode[1];
                int nbX = nbNode[0];
                if(distance[x] + dist < distance[nbX] ){
                    distance[nbX] = distance[x] + dist;
                    queue.add(new int[]{nbX,distance[nbX]});
                }
            }
        }

        int max =-1,visitedNode=0;
        for(int i=1;i<=n;i++){
            if(distance[i] == Integer.MAX_VALUE) visitedNode++;
            max = Math.max(distance[i],max);
        }
        return visitedNode == 0 ? max > 0 ? max:-1 : -1;
    }

    public static Map<Integer, List<int[]>> createGraph(int [][]times,int n){
        Map<Integer,List<int[]>> graphMap = new HashMap<>();
        for(int i=0;i<=n;i++){
            graphMap.put(i,new ArrayList<>());
        }
        for(int time[]:times){
            graphMap.get(time[0]).add(new int[]{time[1],time[2]});
        }
        return graphMap;
    }


}
