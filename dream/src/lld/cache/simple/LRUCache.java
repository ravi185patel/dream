package lld.cache.simple;

import lld.cache.common.Node;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    public Node head;
    public Node tail;
    public Map<String, Node> hmap;

    public LRUCache(){
        hmap = new HashMap<>();
    }

    public void put(String key,String value){
        if(hmap.containsKey(key)){
            Node node = hmap.get(key);
            node.value = value;
        }else{
            Node node = new Node(key,value);
            if(hmap.size() >= 2){
                removeTail();
            }
            hmap.put(key,node);
            addToHead(node);
        }
    }

    public void get(String key){
        Node node = hmap.getOrDefault(key,null);
        if(node == null){
            return;
        }else{
            remove(node);
            addToHead(node);
        }
    }

    public void addToHead(Node node){
        node.next=null;
        node.prev = null;
        if(head == null){
            head = node;
            tail = node;
            return;
        }
        node.next = head;
        head.prev = node;
        head = node;
    }

    public void remove(Node node){
        if(head == node){
            head = head.next;
        }
        if(tail == node){
            tail = tail.prev;
        }

        Node next = node.next;
        Node prev = node.prev;
        if(prev != null){
            prev.next = next;
        }
        if(next != null){
            next.prev = prev;
        }
        node.next=null;
        node.prev = null;
    }

    public void print(){
        Node temp = head;
        while(temp != null){
            System.out.println(temp.key+" >> ");
            temp = temp.next;
        }
    }

    public void removeTail(){
        if(tail == null) return;
        Node node = tail;
        hmap.remove(node.key);
        tail = tail.prev;
        if(tail != null){
            tail.next = null;
        }else{
            head = null;
        }
        node.prev = null;
    }

    public static void main(String args[]){
        LRUCache lruCache = new LRUCache();
        lruCache.put("ravi","123");
        lruCache.put("hitesh","321");
        lruCache.put("kkk","929292");
        lruCache.print();
        lruCache.get("kkk");
        lruCache.put("ravi","123");
        lruCache.print();
    }
}
