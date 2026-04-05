package other;

import graph.basic.UnionDisjointByRankOrWeight;

public class IncrementalEvenWeightedCycleQueries {
    public static void main(String[] args) {
        System.out.println(numberOfEdgesAdded(3,
                new int[][]{
                        {0,1,1},{1,2,1},{0,2,1}
                }));
        System.out.println(numberOfEdgesAdded(3,
                new int[][]{
                        {0,1,1},{1,2,1},{0,2,0}
                }));
        System.out.println(numberOfEdgesAdded(4,
                new int[][]{
                        {0,1,1},{1,2,1},{0,2,0},{0,3,1}
                }));

        System.out.println(numberOfEdgesAdded(5,
                new int[][]{
                        {0,1,1},{1,2,1},{0,2,0},{0,3,1},{0,4,1}
                }));
        System.out.println(numberOfEdgesAdded(3,
                new int[][]{
                        {0,1,0},{0,2,1},{1,2,0}
                }));
    }

    public static int numberOfEdgesAdded(int n, int[][] edges) {
        int party[]=new int[n];
        int parent[]=new int[n];
        int rank[]=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
            party[i]=0;
        }

        int count=0;
        for(int []e:edges){
            int u = e[0];
            int v = e[1];
            int w = e[2];
            int parentU = find(u,parent);
            int parentV = find(v,parent);

            int partyU = getParty(u,parent,party);
            int partyV = getParty(v,parent,party);
            if(parentU == parentV){
                if((partyU ^ partyV) == w){
                    count++;
                }
            }else{
                if(rank[parentU] < rank[parentV]){
                    parent[parentU] = parentV;
                    party[partyU] = partyU ^ partyV ^ w;
                }else{
                    parent[parentV] = parentU;
                    party[partyV] = partyU ^ partyV ^ w;
                    if(rank[parentU] == rank[parentV]) rank[parentU]++;
                }
                count++;
            }
        }
        return count;
    }

    public static int find(int x,int[]parent){
        if(x == parent[x]) return x;
        return parent[x]=find(parent[x],parent);
    }

    public static int getParty(int x,int parent[],int party[]){
        int res=0;
        while(x != parent[x]){
            res ^= party[x];
            x = parent[x];
        }
        return res;
    }
}
