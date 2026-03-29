import java.util.*;

class EventManager {

//    PriorityQueue<Integer> pq;
    Map<Integer,Integer> eventMap;
    TreeMap<int[],Integer> treeMap;
    public EventManager(int[][] events) {
        eventMap = new HashMap<>();
        treeMap = new TreeMap<>((a, b) -> a[1] != b[1] ?  b[1] - a[1] : a[0] - b[0] );

        for(int event[]:events){
            eventMap.put(event[0],event[1]);
            treeMap.put(event,event[0]);
        }
//        eventMap = new HashMap<>();
//        pq = new PriorityQueue<>((i,j) -> {
//            int p1 = eventMap.get(i);
//            int p2 = eventMap.get(j);
//            if (p1 != p2) {
//                return p2 - p1;
//            } else {
//                return i-j;
//            }
//        });
//
//        for(int event[]:events){
//            eventMap.put(event[0],event[1]);
//            pq.add(event[0]);
//        }

    }

    public void updatePriority(int eventId, int newPriority) {
//         pq.remove(eventId);
//        eventMap.put(eventId,newPriority);
//         pq.add(eventId);

        int oldPr = eventMap.get(eventId);
        treeMap.remove(new int[]{eventId,oldPr});
        treeMap.put(new int[]{eventId,newPriority},eventId);
    }

    public int pollHighest() {
        if(treeMap.isEmpty()) return -1;
        int[] key = treeMap.firstKey();
        treeMap.remove(key);
        int eid = key[0];
        eventMap.remove(eid);
        return eid;

    }
}

/**
 * Your EventManager object will be instantiated and called as such:
 * EventManager obj = new EventManager(events);
 * obj.updatePriority(eventId,newPriority);
 * int param_2 = obj.pollHighest();
 */
public class DesignEventManager {
    public static void main(String[] args) {
        EventManager obj = new EventManager(new int[][]{
                {5, 7},{2, 7},{9, 4}
        });
        obj.updatePriority(9,7);
//         int param_2 = obj.pollHighest();
         System.out.println(obj.pollHighest());
         System.out.println(obj.pollHighest());
         System.out.println(obj.pollHighest());
         
         obj = new EventManager(new int[][]{
                 {4, 1},{7, 2}
        });
//         int param_2 = obj.pollHighest();
         System.out.println(obj.pollHighest());
         System.out.println(obj.pollHighest());
         System.out.println(obj.pollHighest());
    }
}
