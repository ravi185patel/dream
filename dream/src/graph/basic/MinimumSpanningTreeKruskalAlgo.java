package graph.basic;

import java.util.*;

public class MinimumSpanningTreeKruskalAlgo {
    public static void main(String[] args) {
        System.out.println(primsAlgo(5,new int[][]{
                 {0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}
        }));
    }
    public static int primsAlgo(int V,int [][] edges){
        Map<Integer, List<int[]>> graphMap = createGraph(V,edges);
        Queue<int[]> queue = new PriorityQueue<>((i, j) -> i[1]-j[1]);
        int dis[]=new int[V];
        boolean visited[]=new boolean[V];
        Arrays.fill(dis,Integer.MAX_VALUE);
        queue.add(new int[]{0,0});
        dis[0]=0;
        int weight =0;
        while(!queue.isEmpty()) {
            int node[] = queue.poll();
            if(visited[node[0]]) continue; // why it is not worked inside loop
            // reason :- you need to process min edges of each node not path
            // so 0 -> 1(2), 3 -> 1(8),0-> 3(6) now 1 has 2 edges from 1 and 3
            // which is small 0 -> 1 if you put inside loop it won't process 3->1 as it is already visited
            // and even if you use distance array it will be exclude as it has lower distance
            /*
                Because the priority queue can contain duplicates:
                Same node may be pushed multiple times with different weights
                Only the smallest one should be used
                Others must be ignored → that’s what visited[] does

                🧠 Key insight
                Inner loop check → avoids pushing unnecessary edges
                Outer check (visited[node]) → avoids processing duplicates from PQ
                👉 Both are needed for correctness + efficiency

                The outer visited[node] check is about “Should I process this node at all?”
                The inner check is about “Should I consider this neighbor?”

                | Check                     | Purpose                   | Location         |
                | ------------------------- | ------------------------- | ---------------- |
                | `if (visited[node])`      | Skip duplicate PQ entries | **Outside loop** |
                | `if (!visited[neighbor])` | Avoid unnecessary pushes  | **Inside loop**  |

             */
            weight+=node[1];
            visited[node[0]]= true;
            for (int nbNode[] : graphMap.get(node[0])) {
                if(visited[nbNode[0]] == false){
                    queue.add(new int[]{nbNode[0], nbNode[1]});
                }
            }
        }

        return weight;
    }

    public static Map<Integer,List<int[]>> createGraph(int V,int edges[][]){
        Map<Integer,List<int[]>> graphMap = new HashMap<>();
        for(int i=0;i<V;i++){
            graphMap.put(i,new ArrayList<>());
        }
        for(int edge[]:edges){
            graphMap.get(edge[0]).add(new int[]{edge[1],edge[2]});
            graphMap.get(edge[1]).add(new int[]{edge[0],edge[2]});
        }
        return graphMap;
    }
}
