package graph.takeuforward;

import graph.common.CommonUtil;
import graph.common.Pair;

import java.util.*;

public class ShortestPathInUAG {
    public static void main(String[] args) {
        CommonUtil.print(findShortestPath(3,3,2,new int[][][]{
                        {{1, 1}, {2, 6}},
                        {{2, 3}, {0, 1}},
                        {{1, 3}, {0, 6}}
        }));
        CommonUtil.print(findShortestPath(2,0,0,new int[][][]{
                        {{1, 9}, {0,9}}
        }));

    }

    public static int[] findShortestPath(int V, int E,int s,int [][][]edges){
        Map<Integer, List<Pair>> graphMap = createGraph(V,E,edges);
//        return topoSort(m,n,graphMap);
//        return pureBfs(s,V,E,graphMap);
        return dijkstra(s,V,E,graphMap);
    }

    public static int[] dijkstra(int sr,int m,int n,Map<Integer, List<Pair>> graphMap){
        Queue<Pair> queue = new PriorityQueue<>((p1,p2)-> p1.distance-p2.distance);
        queue.add(new Pair(sr,0));
        int distance[]=new int[m];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[sr]=0;
        while(!queue.isEmpty()){
            Pair node = queue.poll();
            for(Pair pair:graphMap.getOrDefault(node.node,new ArrayList<>())){
                int dis = pair.distance + distance[node.node];
                if(distance[pair.node] > dis ){
                    distance[pair.node]=dis;
                    queue.add(new Pair(pair.node,dis));
                }
            }
        }
        for(int i=0;i<distance.length;i++){
            distance[i] = distance[i] == Integer.MAX_VALUE ? -1: distance[i];
        }
        return distance;
    }

    public static int[] pureBfs(int sr,int m,int n,Map<Integer, List<Pair>> graphMap){
        // default source is zero assume
        Queue<Integer> queue = new LinkedList<>();
        queue.add(sr);
        int distance[]=new int[m];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[sr]=0;
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

    public static Map<Integer,List<Pair>> createGraph(int V,int E,int [][][]edges){
        Map<Integer,List<Pair>> graphMap = new HashMap<>();
        for(int i=0;i<edges.length;i++){
                for(int edge[]:edges[i]){
                    graphMap.putIfAbsent(i, new ArrayList<>());
                    graphMap.get(i).add(new Pair(edge[0], edge[1]));
                }

        }
        return graphMap;
    }
}
