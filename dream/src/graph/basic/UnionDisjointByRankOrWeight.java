package graph.basic;

import java.util.Arrays;

public class UnionDisjointByRankOrWeight {
    public int parent[];
    public int rank[];
    public int size[];

    public UnionDisjointByRankOrWeight(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];
        Arrays.fill(size,1);
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
    }

    public int find(int x){
        if(x == parent[x]) return x;
        return parent[x]=find(parent[x]);
    }

    /*
    Union by Rank: “Attach smaller height tree under taller one”

    | Case              | Action                     |
    | ----------------- | -------------------------- |
    | rank[X] > rank[Y] | attach Y under X           |
    | rank[X] < rank[Y] | attach X under Y           |
    | equal             | attach any + increase rank |

    ⚡ Combined Effect (Very Important)
    With:
    Path Compression (find)
    Union by Rank/Size

    👉 Time complexity becomes:
    Almost O(1) (amortized, technically α(n))

    🧠 Final takeaway
    parent[] → structure
    rank[] → tree height (used in unionRank)
    size[] → number of nodes (used in unionWeight)

    👉 NEVER compare parent[] values
    👉 ALWAYS compare rank[] or size[]


    🎯 Intuition Summary
    Prim’s → “Grow tree step by step”
    Kruskal’s → “Pick best edges globally”

    🧠 One-line memory trick
    Prim’s = expand nodes
    Kruskal’s = select edges

    Union builds the groups, find identifies the groups.
     */

    public void union(int x,int y){
        int xParent = find(x);
        int yParent = find(y);
        if(xParent != yParent){
            parent[y]=x;
        }
        //etc code will be added based on problem condition
    }

    public void unionRank(int x,int y){
        int parentX = find(x);
        int parentY = find(y);
        if(parentX == parentY) return;
        if(rank[parentX] > rank[parentY]){
            parent[parentY]=parentX;
        }else if(rank[parentX] < rank[parentY]){
            parent[parentX]=parentY;
        }else{
            parent[parentX]=parentY;
            rank[parentY]++;
        }
    }

    /*
    Union by Size: “Attach smaller set under bigger set”
     */
    public boolean unionWeight(int x,int y){
        int parentX = find(x);
        int parentY = find(y);
        if(parentX == parentY) return false;
        if(size[parentX] >= size[parentY]){
            size[parentX]+=size[parentY];
            parent[parentY]=parentX;
        }else if(size[parentX] < size[parentY]){
            size[parentY]+=size[parentX];
            parent[parentX]=parentY;
        }
        return true;
    }

    public int getSize(int node) {
        return size[find(node)];
    }
}
