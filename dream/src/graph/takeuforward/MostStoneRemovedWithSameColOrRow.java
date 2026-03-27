package graph.takeuforward;

import graph.basic.UnionDisjointByRankOrWeight;

import java.util.HashMap;
import java.util.Map;

public class MostStoneRemovedWithSameColOrRow {
    public static void main(String[] args) {
        System.out.println(removeStones(new int[][]{
                {0,0},{0,1},{1,0},{1,2},{2,1},{2,2}                
        }));
        System.out.println(removeStones(new int[][]{
                {0,0},{0,2},{1,1},{2,0},{2,2}
        }));
    }

    public static int removeStones(int[][] stones) {
        int n = stones.length;
        int maxRow = Integer.MIN_VALUE,maxCol = Integer.MIN_VALUE;
        for(int stone[]:stones){
            maxRow = Math.max(maxRow,stone[0]);
            maxCol = Math.max(maxCol,stone[1]);
        }
        UnionDisjointByRankOrWeight ds = new UnionDisjointByRankOrWeight(maxRow+maxCol+2);
        Map<Integer,Integer> stoneNodes = new HashMap<>();
        for(int i=0;i<n;i++){
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + nodeRow + 1;
            ds.unionWeight(nodeRow,nodeCol);
            stoneNodes.put(nodeRow,1);
            stoneNodes.put(nodeCol,1);
        }

        int cnt=0;
        for(Map.Entry<Integer,Integer> entry: stoneNodes.entrySet()){
            if(ds.find(entry.getKey()) == entry.getKey()){
                cnt++;
            }
        }
        return n-cnt;
    }

}
