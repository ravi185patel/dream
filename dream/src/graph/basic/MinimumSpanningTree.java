package graph.basic;

import java.util.*;

/*
| Feature        | Prim’s         | Kruskal’s     |
| -------------- | -------------- | ------------- |
| Approach       | Node-based     | Edge-based    |
| Data Structure | Priority Queue | Union-Find    |
| Cycle Handling | visited[]      | Disjoint Set  |
| Graph Type     | Dense graphs   | Sparse graphs |

 */
public class MinimumSpanningTree {
    public static void main(String[] args) {
        System.out.println(kruskalAlgo(5,new int[][]{
                 {0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}
        }));
    }
    public static int kruskalAlgo(int V,int [][] edges){
        UnionDisjointByRankOrWeight disjointByRankOrWeight = new UnionDisjointByRankOrWeight(V);
        Arrays.sort(edges,(i,j) -> i[2]-j[2]);
        int weight =0;
        for(int edge[]:edges){
            if(disjointByRankOrWeight.find(edge[0]) != disjointByRankOrWeight.find(edge[1])){
                weight+=edge[2];
                disjointByRankOrWeight.unionRank(edge[0],edge[1]);
            }
        }

        return weight;
    }
}
