package company.google.heap;

import java.util.*;


class Request {
    int id;
    int pickup;
    int drop; // return time

    Request(int id, int pickup, int drop) {
        this.id = id;
        this.pickup = pickup;
        this.drop = drop;
    }
}

class CarUsage {
    int returnTime;
    int carId;

    CarUsage(int returnTime, int carId) {
        this.returnTime = returnTime;
        this.carId = carId;
    }
}
public class CarRentalOptimization {
    public static void main(String[] args) {
        int N = 3;

        List<Request> requests = Arrays.asList(
                new Request(1, 0, 5),
                new Request(2, 2, 7),
                new Request(3, 5, 9)
        );

        Map<Integer, Integer> result = assignCars(requests);

        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            System.out.println("Request " + entry.getKey() + " -> Car " + entry.getValue());
        }
    }

    public static Map<Integer, Integer> assignCars(List<Request> requests) {

        // Sort by pickup time
        requests.sort(Comparator.comparingInt(r -> r.pickup));

        // Min-heap based on earliest return time
        PriorityQueue<CarUsage> heap = new PriorityQueue<>(
                Comparator.comparingInt(c -> c.returnTime)
        );

        Map<Integer, Integer> assignment = new HashMap<>();
        int carCount = 0;

        for (Request req : requests) {

            if (!heap.isEmpty() && heap.peek().returnTime <= req.pickup) {
                // Reuse car
                CarUsage available = heap.poll();
                assignment.put(req.id, available.carId);

                heap.offer(new CarUsage(req.drop, available.carId));
            } else {
                // Allocate new car
                carCount++;
                assignment.put(req.id, carCount);

                heap.offer(new CarUsage(req.drop, carCount));
            }
        }

        System.out.println("Minimum cars used: " + carCount);
        return assignment;
    }
}
