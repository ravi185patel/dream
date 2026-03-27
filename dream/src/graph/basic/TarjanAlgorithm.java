package graph.basic;

import java.util.Arrays;
import java.util.List;

public class TarjanAlgorithm {
    static int timer=0;
    public static void main(String[] args) {

    }
    public static void dfs(int node,int parent,List<List<Integer>> adj
    , boolean[] visited,int[] tin,int []low,List<List<Integer>> bridges){
        visited[node]=true;
        tin[node]=low[node]=timer++;
        //An edge is a bridge if there is no alternate path to go around it.
        // tin[node] When node is first visited in DFS
        // low[node] 👉 “Earliest (smallest) time reachable from this node”
        //tin[] = “when I entered”
        //low[] = “how far back I can go”
        for(int nb:adj.get(node)){
            if(nb == parent) continue;
            if(!visited[nb]){
                dfs(nb,node,adj,visited,tin,low,bridges);
                low[node]=Math.min(low[node],low[nb]);

                // check bridge nb to node (1-2)
                if(low[nb] > tin[node]){ //Neighbor cannot reach node or any ancestor → edge is critical → bridge
                    bridges.add(Arrays.asList(node,nb));
                //  edge (1-2) is a bridge
                    //2 cannot reach back to 1 or above → no alternate path
                }
            }else{
                low[node]=Math.min(low[node],tin[nb]);
            }
        }
    }
}
