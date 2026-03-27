package graph.basic;

import java.util.Arrays;
import java.util.List;

public class BridgeAndArticulatePointInGraph {
    static int timer=0;
    public static void main(String[] args) {

    }

    public static void dfsBridge(int node,int parent,List<List<Integer>> adj
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
                dfsBridge(nb,node,adj,visited,tin,low,bridges);
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

    public static void dfsArticulate(int node,int parent,List<List<Integer>> adj
            , boolean[] visited,int marked[],int[] tin,int []low){
        visited[node]=true;
        tin[node]=low[node]=timer++;
        //An edge is a bridge if there is no alternate path to go around it.
        // tin[node] When node is first visited in DFS
        // low[node] 👉 “Earliest (smallest) time reachable from this node”
        //tin[] = “when I entered”
        //low[] = “how far back I can go”
        int child=0;
        for(int nb:adj.get(node)){
            if(nb == parent) continue;
            if(!visited[nb]){
                dfsArticulate(nb,node,adj,visited,marked,tin,low);
                low[node]=Math.min(low[node],low[nb]);

                if(low[nb] >= tin[node] && parent != -1){
                    marked[node]=1;
                }
                child++;
            }else{
                low[node]=Math.min(low[node],tin[nb]);
            }
        }
        if(parent == -1 && child > 1){
            marked[node]=1;
        }
    }

    /*
    articulate point.
    for(int i=0;i<n;i++){
        if(marked[i] == 1){
            ans.add(i);
        }
    }
     */
}
