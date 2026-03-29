package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidTree {
    public static void main(String[] args) {
        System.out.println(validTree(5,
                new int[][]{
                        {0, 1},{0, 2},{0, 3},{1, 4}
                }));
        System.out.println(validTree(5,
                new int[][]{
                        {0, 1},{1, 2},{2, 3},{1, 3},{1, 4}
                }));
    }
    public static boolean validTree(int n, int[][] edges) {
        Map<Integer,List<Integer>> adjList = createMap(n,edges);
        boolean visited[]=new boolean[n];
        int count=0;
        for(int i=0;i<n;i++){
            if(visited[i] == false){
                if(isCycle(i,-1,visited,adjList)){
                    return false;
                }
                count++;
            }
        }
        return count == 1;
    }

    public static Map<Integer,List<Integer>> createMap(int n,int edges[][]){
        Map<Integer,List<Integer>> adjList = new HashMap<>(n);
        for(int edge[]:edges){
            adjList.putIfAbsent(edge[0],new ArrayList<>());
            adjList.putIfAbsent(edge[1],new ArrayList<>());
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        return adjList;
    }

    public static boolean isCycle(int node, int parent,boolean visited[], Map<Integer, List<Integer>> adjList){
        visited[node]=true;
        for(int nbNode:adjList.getOrDefault(node,new ArrayList<>())){
            if(visited[nbNode] == false){
                if(isCycle(nbNode,node,visited,adjList)){
                    return true;
                }
            }else if(parent != nbNode){
                return true;
            }
        }
        return false;
    }
}
