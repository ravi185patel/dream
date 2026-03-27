package graph.takeuforward;

import graph.basic.UnionDisjointByRankOrWeight;
import graph.common.CommonUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.*;



public class NumberOfIslandsII {

    public static void main(String[] args) {
        System.out.println(
                numIslands2(4,5,new int[][]{
                        {1,1},{0,1},{3,3},{3,4}
                })
        );
    }

    static class DSU {
        int[] parent, size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }

        boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px == py) return false; // already connected

            if (size[px] >= size[py]) {
                parent[py] = px;
                size[px] += size[py];
            } else {
                parent[px] = py;
                size[py] += size[px];
            }
            return true; // union happened
        }
    }

        public static List<Integer> numIslands2(int m, int n, int[][] positions) {

            DSU dsu = new DSU(m * n);
            boolean[][] grid = new boolean[m][n];

            List<Integer> result = new ArrayList<>();
            int count = 0;

            int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

            for (int[] pos : positions) {
                int r = pos[0], c = pos[1];

                // ❗ if already land → no change
                if (grid[r][c]) {
                    result.add(count);
                    continue;
                }

                grid[r][c] = true;
                count++; // new island

                int node = r * n + c;

                for (int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc]) {
                        int adjNode = nr * n + nc;

                        // 🔥 if union happens → reduce island count
                        if (dsu.union(node, adjNode)) {
                            count--;
                        }
                    }
                }

                result.add(count);
            }

            return result;
        }
}
