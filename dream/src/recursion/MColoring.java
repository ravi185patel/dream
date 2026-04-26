package recursion;

import java.util.*;

public class MColoring {
    public static void main(String[] args) {
        System.out.println(
                isPossible(4,3,5,new int[][]{
                        {0, 1},
                        {1, 2},
                        {2, 3},
                        {3, 0},
                        {0, 2}
                })
        );

        System.out.println(
                isPossible(3,2,3,new int[][]{
                        {0, 1},
                        {1, 2},
                        {0, 2}
                })
        );
    }
    // M most color
    public static boolean isPossible(int N,int M,int E,int edges[][]){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0;i<N;i++){
            graph.put(i,new ArrayList<>());
        }
        for(int edge[]:edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int color[]=new int[N];
        Arrays.fill(color,-1);
        return solve(0,N,M,graph,color);
    }

    public static boolean solve(int node,int N,int M,Map<Integer,List<Integer>> graph,int color[]){
        if(node == N){
            return true;
        }

        for(int i=0;i<M;i++){
            if(isSafe(node,i,graph,color)){
                color[node]=i;
                if(solve(node+1,N,M,graph,color)){
                    return true;
                }
                color[node]=-1; // why zero won't work because you are taking 0 to m-1 color and
                // 0 initial value of each node position in color array.
            }
        }
        return false;
    }

    public static boolean isSafe(int node,int cl,Map<Integer,List<Integer>> graph,int color[]){
        for(int nbNode:graph.get(node)){
            if(color[nbNode] == cl){
                return false;
            }
        }
        return true;
    }
}
