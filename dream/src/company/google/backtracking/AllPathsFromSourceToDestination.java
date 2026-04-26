package company.google.backtracking;

import java.util.*;

public class AllPathsFromSourceToDestination {
    public static void main(String[] args) {
        System.out.println(
                leadsToDestination(3,new int[][]{
                        {0,1},{0,2}
                },0,2)
        );

        System.out.println(
                leadsToDestination(4,new int[][]{
                        {0,1},
                        {0,3},
                        {1,2},
                        {2,1}
                },0,3)
        );

        System.out.println(
                leadsToDestination(4,new int[][]{
                        {0,1},
                        {0,2},
                        {1,3},
                        {2,3}
                },0,3)
        );
        System.out.println(
                leadsToDestination(36,new int[][]{
                        {0,15},{1,25},{2,25},{3,10},{4,30},{5,10},{6,22},{7,24},{8,29},{9,21},{10,29},{11,35},{12,30},{13,15},{14,31},{15,17},{16,31},{17,27},{18,22},{19,30},{20,30},{21,27},{22,26},{23,28},{24,35},{25,29},{26,28},{27,32},{28,32},{29,32},{30,34},{31,32},{32,33},{33,35},{34,35},{14,22},{0,11},{19,26},{23,34},{31,29},{33,12},{31,17},{27,1},{20,3},{9,28},{22,30},{14,16},{10,18},{22,19},{18,9},{0,12}
                },0,35)
        );
    }
    private enum Color { GRAY, BLACK }

    private static boolean leadsToDest(List<List<Integer>> graph,
                                    int node, int dest, Color[] states) {
        /* If node is already being
        processed (GRAY), cycle detected */
            if (states[node] != null) {
                return states[node] == Color.BLACK;
            }

            // If leaf node, must be destination
            if (graph.get(node).isEmpty()) {
                return node == dest;
            }

            // Mark as being processed (GRAY)
            states[node] = Color.GRAY;

            // Check all neighbors recursively
            for (int next : graph.get(node)) {
                if (!leadsToDest(graph, next, dest, states)) {
                    return false;
                }
            }

            // Mark as fully processed (BLACK)
            states[node] = Color.BLACK;
            return true;
        }
    private static boolean leadsToDest1(List<List<Integer>> graph,
                                       int node, int dest, boolean visited[],
                                        boolean [] pathVisited) {
        /* If node is already being
        processed (GRAY), cycle detected */
        if (visited[node]) {
            return pathVisited[node];
        }

        // If leaf node, must be destination
        if (graph.get(node).isEmpty()) {
            return node == dest;
        }

        visited[node]=true;
        pathVisited[node]=false;

        // Check all neighbors recursively
        for (int next : graph.get(node)) {
            if (!leadsToDest1(graph, next, dest, visited,pathVisited)) {
                return false;
            }
        }

        pathVisited[node]=false;
        return true;
    }
        // Build adjacency list from edges
        private static List<List<Integer>> buildDigraph(int n, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
            }
            return graph;
        }

    private static boolean leadsToDestIndegree(List<List<Integer>> graph,
                                        int n,int sr, int dest) {
        int inDegree[]=new int[n];
        for(int i=0;i<n;i++){
            for(int nbNode:graph.get(i)){
                inDegree[nbNode]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(sr);
        while(!queue.isEmpty()){
            int node = queue.poll();
            if(graph.get(node).isEmpty() && node != dest){
                return false;
            }

            for(int nbNode:graph.get(node)){
                if(inDegree[nbNode] < 0){ // cycle
                    return false;
                }
                inDegree[nbNode]--;
                queue.add(nbNode);
            }
        }

        return true;
    }

    public static boolean dfs(int source,int destination,List<List<Integer>> graph,boolean visited[]){
        if(source == destination){
            return true;
        }

        List<Integer> nbNodes = graph.get(source);
        if(nbNodes.isEmpty()){
            return false;
        }
        for(int nbNode:nbNodes){
            if(visited[nbNode]) return false;
            visited[nbNode]=true;
            if(!dfs(nbNode,destination,graph,visited)){
                return false;
            }
            visited[nbNode]=false;
        }
        return true;
    }
        public static boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
            List<List<Integer>> graph = buildDigraph(n, edges);
            Color[] states = new Color[n];
            boolean visited[]=new boolean[n];
            boolean pathVisited[]=new boolean[n];
            return leadsToDest1(graph, source, destination, visited,pathVisited);
//            return leadsToDestIndegree(graph,n, source, destination);
//            return dfs(source,destination,graph,visited);
        }
    }
