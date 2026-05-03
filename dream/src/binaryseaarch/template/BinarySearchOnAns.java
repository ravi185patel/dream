package binaryseaarch.template;

import java.util.function.Predicate;

public class BinarySearchOnAns {

    public int binarySearchOnAnswer(int minAnswer, int maxAnswer,
                                    Predicate<Integer> isFeasible) {
        int left = minAnswer;
        int right = maxAnswer;
        int result = maxAnswer;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isFeasible.test(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}
