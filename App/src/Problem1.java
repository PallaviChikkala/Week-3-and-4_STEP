import java.util.Arrays;

public class Problem1 {

    // Linear Search: find threshold in unsorted array
    public static void linearSearch(int[] risks, int target) {
        int comparisons = 0;
        int foundIndex = -1;
        for (int i = 0; i < risks.length; i++) {
            comparisons++;
            if (risks[i] == target) {
                foundIndex = i;
                break;
            }
        }
        System.out.println("Linear search for threshold " + target + ":");
        if (foundIndex != -1) {
            System.out.println("Found at index " + foundIndex);
        } else {
            System.out.println("Not found");
        }
        System.out.println("Comparisons: " + comparisons);
    }

    // Binary Search variant: find floor and ceiling
    public static void binaryFloorCeiling(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int comparisons = 0;
        int floor = Integer.MIN_VALUE;
        int ceiling = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (risks[mid] == target) {
                floor = risks[mid];
                ceiling = risks[mid];
                break;
            } else if (risks[mid] < target) {
                floor = risks[mid];
                low = mid + 1;
            } else {
                ceiling = risks[mid];
                high = mid - 1;
            }
        }

        System.out.println("Binary search for threshold " + target + ":");
        if (floor != Integer.MIN_VALUE) {
            System.out.println("Floor (largest ≤ target): " + floor);
        } else {
            System.out.println("Floor: none");
        }
        if (ceiling != Integer.MAX_VALUE) {
            System.out.println("Ceiling (smallest ≥ target): " + ceiling);
        } else {
            System.out.println("Ceiling: none");
        }
        System.out.println("Comparisons: " + comparisons);
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};
        Arrays.sort(risks); // ensure sorted for binary search
        System.out.println("Sorted risks: " + Arrays.toString(risks));

        // Linear search demo (unsorted threshold match)
        linearSearch(risks, 30);

        // Binary search demo (floor and ceiling)
        binaryFloorCeiling(risks, 30);
    }
}