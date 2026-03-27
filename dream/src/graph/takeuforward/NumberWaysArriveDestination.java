package graph.takeuforward;

import java.util.*;

public class NumberWaysArriveDestination {
    public static void main(String[] args) {
        System.out.println(countPaths(7,
                new int[][]{
                        {0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}
        }));

        System.out.println(countPaths(2,
                new int[][]{
                        {1,0,10}
                }));
    }
    public static int countPaths(int n, int[][] roads) {
        Map<Integer, List<int[]>> graphMap = createGraph(n,roads);
        return disjk(n,graphMap);
    }

    public static int disjk(int n, Map<Integer, List<int[]>> graphMap){
        Queue<int[]> queue = new PriorityQueue<>((i,j)->i[1]-j[1]);
        int distance []=new int[n];
        int ways[]=new int[n];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[0]=0;
        ways[0]=1;
        queue.add(new int[]{0,0});
        while(!queue.isEmpty()){
            int pair[]=queue.poll();
            int node = pair[0];
            int dis = pair[1];
            for(int[] road:graphMap.get(node)){
                int nbRoad = road[0];
                int nbDis = road[1];
                if(dis + nbDis < distance[nbRoad]){
                    distance[nbRoad] = dis + nbDis;
                    queue.add(new int[]{road[0],distance[nbRoad]});
                    ways[nbRoad]=ways[node];
                }else if(dis + nbDis == distance[nbRoad]){
                    distance[nbRoad] = dis + nbDis;
                    ways[nbRoad]+= ways[node];
                }
            }
        }
        return ways[n-1];
    }

    public static Map<Integer, List<int[]>> createGraph(int n, int[][]roads){
        Map<Integer, List<int[]>> graphMap= new HashMap<>();
        for(int i=0;i<n;i++){
            graphMap.put(i,new ArrayList<>());
        }
        for(int road[]:roads){
            graphMap.get(road[0]).add(new int[]{road[1],road[2]});
            graphMap.get(road[1]).add(new int[]{road[0],road[2]});
        }
        return graphMap;
    }
}
