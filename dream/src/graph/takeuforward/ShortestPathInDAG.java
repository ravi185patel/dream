package graph.takeuforward;

import graph.common.CommonUtil;
import graph.common.Pair;

import java.util.*;

public class ShortestPathInDAG {
    public static void main(String[] args) {
        CommonUtil.print(findShortestPath(6,7,new int[][]{
                {0,1,2}, {0,4,1}, {4,5,4},
                {4,2,2}, {1,2,3}, {2,3,6}, {5,3,1}
//                ,{3,100,1000}
        }));

        CommonUtil.print(findShortestPath(7,8,new int[][]{
                {0,4,2},{0,5,3},{5,4,1},{4,6,3},{4,2,1},{6,1,2},{2,3,3},{1,3,1}
        }));
    }

    public static int[] findShortestPath(int m, int n,int [][]edges){
        Map<Integer, List<Pair>> graphMap = createGraph(m,n,edges);
//        return topoSort(m,n,graphMap);
        return pureBfs(m,n,graphMap);
    }

    public static int[] pureBfs(int m,int n,Map<Integer, List<Pair>> graphMap){
        // default source is zero assume
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int distance[]=new int[m];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[0]=0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(Pair pair:graphMap.getOrDefault(node,new ArrayList<>())){
                int dis = pair.distance + distance[node];
                if(distance[pair.node] > dis ){
                    distance[pair.node]=dis;
                    queue.add(pair.node);
                }
            }
        }
        for(int i=0;i<distance.length;i++){
             distance[i] = distance[i] == Integer.MAX_VALUE ? -1: distance[i];
        }
        return distance;
    }

    public static int[] topoSort(int m,int n,Map<Integer,List<Pair>> graphMap){
//        return bfs(m,n,graphMap);
        Stack<Integer> stack = new Stack<>();
//        int visited[]=new int[m+1];
        Map<Integer,Boolean> map = new HashMap<>();
        for(int key:graphMap.keySet()){
            map.putIfAbsent(key,false);
            if(map.get(key)==false){
                dfs(key,map,graphMap,stack);
            }
        }

        int distance[]=new int[m];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[0]=0;
        while(!stack.isEmpty()){
            int node = stack.pop();
            if(distance[node] == Integer.MAX_VALUE) continue;; // process each node in sequence
            // not every one
            for(Pair pair:graphMap.getOrDefault(node,new ArrayList<>())){
                int dis = pair.distance + distance[node];
                if(dis < distance[pair.node]){
                    distance[pair.node]=dis;
                    stack.push(pair.node);
                }
            }
        }
        return distance;
    }

    public static void dfs(int node,Map<Integer,Boolean> visited,Map<Integer,List<Pair>> graph,Stack<Integer> stack){
//        visited[node]=true;
        visited.put(node,true);
        for(Pair pair:graph.getOrDefault(node,new ArrayList<>())){
            if(visited.getOrDefault(node,false)==false){
                dfs(pair.node,visited,graph,stack);
            }
        }
        stack.push(node);
    }

    public static int[] bfs(int m,int n,Map<Integer,List<Pair>> graph){

        Map<Integer,Integer> inDegree=new HashMap<>();
        int max=0;
        for(int key:graph.keySet()){
            max = Math.max(max,key);
            inDegree.put(key,inDegree.getOrDefault(key,0));
            for(Pair pair:graph.getOrDefault(key,new ArrayList<>())){
                max = Math.max(max,pair.node);
                inDegree.put(pair.node,inDegree.getOrDefault(pair.node,0)+1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int distance[]=new int[max+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        for(int node:inDegree.keySet()){
            if(inDegree.getOrDefault(node,-1) == 0){
                queue.add(node);
                distance[node]=0;
            }
        }

        while(!queue.isEmpty()){
            int node = queue.poll();

            for(Pair nbPair:graph.getOrDefault(node, new ArrayList<>())){
                inDegree.put(nbPair.node,inDegree.getOrDefault(nbPair.node,0)-1);
                int paidistance = distance[node] +nbPair.distance;  // use map instead of distance
                if(paidistance < distance[nbPair.node]){
                    distance[nbPair.node]=paidistance;
                }
                if(inDegree.get(nbPair.node) == 0){
                    queue.add(nbPair.node);
                }
            }
        }

        for(int i=0;i<distance.length;i++){
            distance[i] = distance[i] == Integer.MAX_VALUE ? -1:distance[i];
        }

        return distance;
    }

    public static Map<Integer,List<Pair>> createGraph(int m,int n,int [][]edges){
        Map<Integer,List<Pair>> graphMap = new HashMap<>();
        for(int edge[]:edges){
            graphMap.putIfAbsent(edge[0],new ArrayList<>());
            graphMap.get(edge[0]).add(new Pair(edge[1],edge[2]));
        }
        return graphMap;
    }
}
