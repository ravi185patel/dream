import common.CommonUtil;

import java.util.*;

public class Solution2 {

    public static void main(String[] args) {
/*        CommonUtil.print(
                colorGrid(3,3,new int[][]{
                        {0,0,1},{2,2,2}
                })
        );
        CommonUtil.print(
                colorGrid(3,3,new int[][]{
                        {0,1,3},{1,1,5}
                })
        );
        CommonUtil.print(
                colorGrid(2,2,new int[][]{
                        {1,1,5}
                })
        );
        CommonUtil.print(
                colorGrid(1,2,new int[][]{
                        {0,1,923912},
                        {0,0,870304}
                })
        );
        CommonUtil.print(
                colorGrid(1,5,new int[][]{
                        {0,0,5},
                        {0,3,4},
                        {0,2,1}
                })
        );*/

        System.out.println(countGoodIntegersOnPath(8,9,"DDDRRR"));
        System.out.println(countGoodIntegersOnPath(123456789,123456790,"DDRRDR"));
//        System.out.println(countGoodIntegersOnPath(Long.parseLong("1288561398769758"),Long.parseLong("1288561398769758"),"RRRDDD"));
    }
    public static int [][] dirs={{-1,0},{0,-1},{1,0},{0,1}};

    public static int[][] colorGrid(int m, int n, int[][] sources) {
        Queue<int[]> queue = new LinkedList<>();
        boolean visited[][]=new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((i,j) -> j[2] - i[2]);
        int grid[][]=new int[m][n];
        for(int cell[]:sources){
            int x = cell[0];
            int y = cell[1];
            grid[x][y]=cell[2];
            visited[x][y]=true;
            pq.add(cell);
        }

        while(!pq.isEmpty()){
            queue.add(pq.poll());
        }

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int cell[]=queue.poll();
                int x =cell[0];
                int y = cell[1];
                for(int dir[]:dirs){
                    int newX = dir[0] + x;
                    int newY = dir[1] + y;
                    if(newX < 0 || newX >= m || newY < 0 || newY >= n || visited[newX][newY]){
                        continue;
                    }
                    grid[newX][newY] = grid[x][y];
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX,newY,grid[newX][newY]});
                }
            }
        }
        return grid;
    }


        static List<Integer> path = new ArrayList<>();
    public static long countGoodIntegersOnPath(long l, long r, String directions) {
        long grid[][]=new long[4][4];
        int x = 0, y = 0;
        path.add(0);

        for (char c : directions.toCharArray()) {
            if (c == 'D') x++;
            else y++;
            path.add(x * 4 + y);
        }
        String num = padTo16(r);  // same padding logic you wrote
        long ans = dfs(0, 0, -1, true, num);

//        System.out.println(Arrays.toString(arr));
        /*for(long i=l;i<=r;i++){

//            fill(grid,i);
//            CommonUtil.print(grid);
//            boolean flag = bfs(grid,directions);
              boolean flag = isPossible(Long.toString(i),directions);
            if(flag){
                ans++;
            }
        }*/
        return ans;
    }

    public static String padTo16(long r){
        String no = Long.toString(r);
        String nos = "";
        for(int i=0;i<(no.length() > (4*4) ? 0 : (4*4)-no.length());i++){
            nos+="0";
        }
        nos+=no;
        return nos;
    }

    static long dfs(int pos, int pathIdx, int prev, boolean tight, String num) {
        // base case
        if (pos == 16) {
            return 1;
        }

        int limit = tight ? num.charAt(pos) - '0' : 9;
        long count = 0;

        for (int d = 0; d <= limit; d++) {
            int newPathIdx = pathIdx;
            int newPrev = prev;

            // if current position is part of path
            if (pathIdx < path.size() && pos == path.get(pathIdx)) {
                if (d < prev) continue;   // enforce non-decreasing
                newPrev = d;
                newPathIdx++;
            }

            count += dfs(
                    pos + 1,
                    newPathIdx,
                    newPrev,
                    tight && (d == limit),
                    num
            );
        }

        return count;
    }
    public static boolean isPossible(String no,String dir){
        String nos = "";
        for(int i=0;i<(no.length() > (4*4) ? 0 : (4*4)-no.length());i++){
            nos+="0";
        }
        nos+=no;
        no = nos;
        boolean flag = true;
        long prev=0;
        int x =0,y=0;
        for(char c:dir.toCharArray()){
            if(c=='D'){
                x++;
            }else if(c=='R'){
                y++;
            }
            if(x >= 4 || y >= 4){
                continue;
            }

            if(no.charAt(x*4+y) >= prev){
                prev = no.charAt(x*4+y);
            }else{
                System.out.println("in else");
                flag = false;
            }
        }
        return flag;
    }

    public static boolean bfs(long grid[][],String dir){
        boolean visited[][]=new boolean[4][4];
        boolean flag = true;
        long prev=grid[0][0];
        int x =0,y=0;
        for(char c:dir.toCharArray()){
            if(c=='D'){
                x++;
            }else if(c=='R'){
                y++;
            }
            if(x >= 4 || y >= 4){
                continue;
            }

            if(grid[x][y] >= prev){
                prev = grid[x][y];
            }else{
                System.out.println("in else");
                flag = false;
            }
        }
        return flag;
    }

    public static void fill(long grid[][],long i){
        int x= 3,y=3;
        while(i > 0){
            long no = i%10;
            i = i/10;
            grid[y][x] = no;
            if(x >= 0){
                x--;
                if(x < 0){
                    x=3;
                    y--;
                }
            }else{
                x=3;
                y--;
            }
        }
    }
}
