package graph.takeuforward;

import java.util.*;

class Airport{
    int flight;
    int cost;

    public Airport(int flight, int cost) {
        this.flight = flight;
        this.cost = cost;
    }
}
public class CheapestFlightWithKStop {
    public static void main(String[] args) {
//        n = 4, flights = [[0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200]], src = 0, dst = 3, k = 1
//        n = 3, flights = [[0,1,100},{1,2,100},{0,2,500]], src = 0, dst = 2, k = 1
//        n = 3, flights = [[0,1,100},{1,2,100},{0,2,500]], src = 0, dst = 2, k = 0
        System.out.println(findCheapestPrice(4,new int[][]{
                {0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}
        },0,3,1));
        System.out.println(findCheapestPrice(3,new int[][]{
                {0,1,100},{1,2,100},{0,2,500}
        },0,2,1));
        System.out.println(findCheapestPrice(3,new int[][]{
                {0,1,100},{1,2,100},{0,2,500}
        },0,2,0));
    }
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer,List<Airport>> flightGraph = createFlighGraph(n,flights);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,0});
        int distance[]=new int[n];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[0]=0;
        while(!queue.isEmpty()){
            int airport[]=queue.poll();
            int flight = airport[0];
            int stops = airport[2];
            if(stops > k) continue;
            for(Airport nbAirport:flightGraph.get(flight)){
                int nbFlight = nbAirport.flight;
                int nbCost = nbAirport.cost;
                if(nbCost + distance[flight] < distance[nbFlight]){
                    distance[nbFlight]=nbCost + distance[flight];
                    queue.add(new int[]{nbFlight,distance[nbFlight],stops+1});
                }
            }
        }
        return distance[dst];
    }

    public static Map<Integer,List<Airport>> createFlighGraph(int n, int[][]flights){
        Map<Integer, List<Airport>> flightGraph = new HashMap<>();
        for(int i=0;i<n;i++){
            flightGraph.put(i,new ArrayList<>());
        }
        for(int flight[]:flights){
            flightGraph.get(flight[0]).add(new Airport(flight[1],flight[2]));
        }
        return flightGraph;
    }
}
