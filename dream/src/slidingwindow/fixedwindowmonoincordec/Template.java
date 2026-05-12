package slidingwindow.fixedwindowmonoincordec;

import java.util.*;

/*
Do I care about ALL elements ordering? Yes
median
kth smallest/largest
ordered dynamic set
balanced halves
 */
class Template {

    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    int leftSize = 0;
    int rightSize = 0;

    // ADD
    void add(int num) {

        // decide where to put number
        if (left.isEmpty() || num <= left.lastKey()) {
            insert(left, num);
            leftSize++;
        } else {
            insert(right, num);
            rightSize++;
        }

        balance();
    }

    // REMOVE
    void remove(int num) {

        // decide which side contains number
        if (num <= left.lastKey()) {
            delete(left, num);
            leftSize--;
        } else {
            delete(right, num);
            rightSize--;
        }

        balance();
    }

    // BALANCE BOTH SIDES
    void balance() {

        // left too big
        while (leftSize > rightSize + 1) {

            int x = left.lastKey();

            delete(left, x);
            leftSize--;

            insert(right, x);
            rightSize++;
        }

        // right bigger
        while (leftSize < rightSize) {

            int x = right.firstKey();

            delete(right, x);
            rightSize--;

            insert(left, x);
            leftSize++;
        }
    }

    // GET MEDIAN
    double median(int k) {

        // odd size
        if (k % 2 == 1) {
            return left.lastKey();
        }

        // even size
        return ((double) left.lastKey() + right.firstKey()) / 2.0;
    }

    // INSERT INTO MAP
    void insert(TreeMap<Integer, Integer> map, int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    // DELETE FROM MAP
    void delete(TreeMap<Integer, Integer> map, int num) {

        map.put(num, map.get(num) - 1);

        if (map.get(num) == 0) {
            map.remove(num);
        }
    }
}