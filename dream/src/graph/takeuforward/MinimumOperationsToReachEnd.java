package graph.takeuforward;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinimumOperationsToReachEnd {
    public static void main(String[] args) {
        System.out.println(minimumOperations(3,3,30,new int[]{2,5,7}));
        System.out.println(minimumOperations(2,1,12,new int[]{3,2}));
        System.out.println(minimumOperations(4,8,320,new int[]{4,6,1,10}));
        System.out.println(minimumOperations(42,54,98,new int[]{
                1,70,25,79,59,63,65,6,46,82,28,62,92,96,43,28,37,92,5,3,54,93,83,22,17,19,96,48,27,72,39,70,13,68,100,36,95,4,12,23,34,74}));
    }
    public static int minimumOperations(int n, int start, int end, int []a) {
        // dfs path exists or not but need to find min op -> bfs and every time one weight increase so bfs + distance
        // or disj
        Arrays.sort(a);
        return findUsingBfs(n,start,end,a);
    }

    public static int findUsingBfs(int n,int start,int end,int []a){
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{start,0});
            boolean visited[]=new boolean[1000];
            visited[start]=true;
            while(!queue.isEmpty()){
                int node[] = queue.poll();
                if(node[0] == end){
                    return node[1];
                }
                for(int ele:a){
                    int newNode = node[0] * ele;
                    newNode = newNode % 1000;
                    if(visited[newNode] == false) {
                        visited[newNode]=true;
                        queue.add(new int[]{newNode, node[1] + 1});
                    }
                }
            }
            return -1;
    }
}
