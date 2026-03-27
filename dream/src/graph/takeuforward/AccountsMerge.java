package graph.takeuforward;

import graph.basic.UnionDisjointByRankOrWeight;

import java.util.*;

public class AccountsMerge {
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "j1@com", "j2@com", "j3@com"));
        accounts.add(Arrays.asList("John", "j4@com"));
        accounts.add(Arrays.asList("Raj", "r1@com", "r2@com"));
        accounts.add(Arrays.asList("John", "j1@com", "j5@com"));
        accounts.add(Arrays.asList("Raj", "r2@com", "r3@com"));
        accounts.add(Arrays.asList("Mary", "m1@com"));

        System.out.println(accountsMerge(accounts));
    }

    public static List<List<String>> accountsMerge1(List<List<String>> accounts) {
        Map<String,Integer> accountMap = new HashMap<>();
        int n=accounts.size();
        UnionDisjointByRankOrWeight ds = new UnionDisjointByRankOrWeight(n);
        for(int i=0;i<n;i++){
            for(int j=1;j<accounts.get(i).size();j++){
                String email = accounts.get(i).get(j);
                if(accountMap.containsKey(email)){
                    accountMap.put(email,i);
                }else{
                    ds.unionWeight(i,accountMap.get(email));
                }
            }
        }

        ArrayList<String>[] mergeAccount= new ArrayList[n];
        Arrays.fill(mergeAccount,new ArrayList<>());
        for(Map.Entry<String,Integer> entry:accountMap.entrySet()){
            String email = entry.getKey();
            int index = ds.find(entry.getValue());
            mergeAccount[index].add(email);
        }

        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergeAccount[i].size() == 0) continue;
            Collections.sort(mergeAccount[i]);
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            for (String it : mergeAccount[i]) {
                temp.add(it);
            }
            res.add(temp);
        }
        return res;
    }
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        UnionDisjointByRankOrWeight ds = new UnionDisjointByRankOrWeight(n);

        HashMap<String, Integer> mapMailNode = new HashMap<String, Integer>();

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (mapMailNode.containsKey(mail) == false) {
                    mapMailNode.put(mail, i);
                } else {
                    ds.unionWeight(i, mapMailNode.get(mail));
                }
            }
        }

        ArrayList<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++) mergedMail[i] = new ArrayList<String>();
        for (Map.Entry<String, Integer> it : mapMailNode.entrySet()) {
            String mail = it.getKey();
            int node = ds.find(it.getValue());
            mergedMail[node].add(mail);
        }

        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (mergedMail[i].size() == 0) continue;
            Collections.sort(mergedMail[i]);
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            for (String it : mergedMail[i]) {
                temp.add(it);
            }
            ans.add(temp);
        }
        return ans;


    }
}
