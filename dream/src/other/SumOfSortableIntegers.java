package other;

public class SumOfSortableIntegers {

    public static void main(String[] args) {
        System.out.println(sortableIntegers(new int[]{3,1,2}));;
        System.out.println(sortableIntegers(new int[]{7,6,5}));;
        System.out.println(sortableIntegers(new int[]{5,6}));;
        System.out.println(sortableIntegers(new int[]{4,4}));;
        System.out.println(sortableIntegers(new int[]{2,3,1}));;
    }
    public static int sortableIntegers(int[] nums) {
        int totalLen = nums.length;
        int answer = 0;
        for (int blockSize = 1; blockSize <= totalLen; blockSize++) {
            if (totalLen % blockSize != 0) continue;
            if (checkSortable(nums, totalLen, blockSize)) answer += blockSize;
        }
        return answer;
    }

    private static boolean checkSortable(int[] nums, int totalLen, int blockSize) {
        int numBlocks = totalLen / blockSize;
        for (int b = 0; b < numBlocks; b++) {
            int startIdx = b * blockSize;
            int circularDescents = 0;
            for (int i = 0; i < blockSize; i++) {
                if (nums[startIdx + i] > nums[startIdx + (i + 1) % blockSize])
                    circularDescents++;
            }
            if (circularDescents > 1) return false;
        }

        // validate two separate array are sorted or not [5,6] [7,8]
        for (int b = 0; b < numBlocks - 1; b++) {
            int s1 = b * blockSize, s2 = (b + 1) * blockSize;
            int maxCur = Integer.MIN_VALUE, minNext = Integer.MAX_VALUE;
            for (int i = 0; i < blockSize; i++) {
                maxCur = Math.max(maxCur, nums[s1 + i]);
                minNext = Math.min(minNext, nums[s2 + i]);
            }
            if (maxCur > minNext) return false;
        }
        return true;
    }

}
