package graph.takeuforward;

import graph.basic.UnionDisjointByRankOrWeight;

/*
here we need to find how many (connected) component
1) dfs - 1 = result
2) bfs - 1 = result
3) disjoin -1 = result
 */
public class MinOpToMakeNetworkConnected {
    public static void main(String[] args) {
        System.out.println(makeConnected(4,new int[][]{
                {0,1},{0,2},{1,2}
        }));
        System.out.println(makeConnected(6,new int[][]{
                {0,1},{0,2},{0,3},{1,2},{1,3}
        }));
        System.out.println(makeConnected(6,new int[][]{
                {0,1},{0,2},{0,3},{1,2}
        }));
    }
    public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        UnionDisjointByRankOrWeight ds = new UnionDisjointByRankOrWeight(n);
        int newConnection=n;
        for(int conn[]:connections){
            int x = conn[0];
            int y = conn[1];
            if(ds.find(y) != ds.find(x)){
                ds.unionRank(x,y);
                newConnection--;
            }
        }

        return newConnection-1;
    }
}
