package graph;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {
    public static void main(String[] args) {

    }

    public static Node cloneGraph(Node node) {
        return dfs(node);
    }

    static Map<Integer,Node> map = new HashMap<>();
    public static Node dfs(Node node){
        if(node ==null) return node;
        if(map.containsKey(node.val)){
            return map.get(node.val);
        }

        Node newNode = new Node(node.val);
        map.put(node.val,newNode);
        for(Node nbNode:node.neighbors){
            newNode.neighbors.add(dfs(node));
        }
        return newNode;
    }

    public static Node bfs(Node node){
        if(node == null) return node;
        Queue<Node> queue = new LinkedList<>();
        map.put(node.val,new Node(node.val));
        queue.add(node);
        Node head=null;
        while(!queue.isEmpty()){
            Node node1 = queue.poll();
            for(Node nbNode:node1.neighbors){
                if(!map.containsKey(nbNode.val)){
                    map.put(nbNode.val,new Node(nbNode.val));
                    queue.add(nbNode);
                }
                map.get(node1.val).neighbors.add(map.get(nbNode.val));
            }
        }

        return map.get(node);
    }
}
