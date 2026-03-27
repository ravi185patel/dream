package graph.basic;

import java.util.*;

public class SSC {
    public static void main(String[] args) {
        System.out.println("The number of strongly connected components is: " + ksarajuAlgo(5, new int[][] {
                {1, 0}, {0, 2},
                {2, 1}, {0, 3},
                {3, 4}
        }));
    }

    public static int ksarajuAlgo(int V,int edges[][]){
        Map<Integer, List<Integer>> graph = createMap(V,edges,false);
        Stack<Integer> stack = new Stack<>();
        boolean visited[]=new boolean[V];
        for(int i=0;i<V;i++){
            if(visited[i] == false){
                dfs(i,graph,visited,stack);
            }
        }

        graph = createMap(V,edges,true);
        Arrays.fill(visited,false);
        int ssc=0;
        while(!stack.isEmpty()){
            int node = stack.pop();
            if(visited[node]==false){
                dfs1(node,graph,visited);
                ssc++;
            }
        }

        return ssc;
    }

    public static void dfs(int node,Map<Integer,List<Integer>> graph,boolean visited[],Stack<Integer> stack){
        visited[node]=true;
        for(int nbNode:graph.getOrDefault(node,new ArrayList<>())){
            if(visited[nbNode]==false) {
                dfs(nbNode, graph, visited, stack);
            }
        }
        stack.add(node);
    }


    public static void dfs1(int node,Map<Integer,List<Integer>> graph,boolean visited[]){
        visited[node]=true;
        for(int nbNode:graph.getOrDefault(node,new ArrayList<>())){
            if(visited[nbNode]==false) {
                dfs1(nbNode, graph, visited);
            }
        }
    }
    public static Map<Integer,List<Integer>> createMap(int V,int edges[][],boolean reverse){
        Map<Integer,List<Integer>> graph = new HashMap<>();
        for(int i=0;i<V;i++){
            graph.put(i,new ArrayList<>());
        }
        for(int edge[]:edges){
            if(reverse) {
                graph.get(edge[1]).add(edge[0]);
            }else{
                graph.get(edge[0]).add(edge[1]);
            }
        }
        return graph;
    }
}
