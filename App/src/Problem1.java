import java.util.Arrays;

public class Problem1 {

    // Linear Search: find first and last occurrence
    public static void linearSearch(String[] logs, String target) {
        int first = -1, last = -1, comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }
        System.out.println("Linear search for " + target + ":");
        if (first != -1) {
            System.out.println("First occurrence at index " + first);
            System.out.println("Last occurrence at index " + last);
        } else {
            System.out.println("Not found");
        }
        System.out.println("Comparisons: " + comparisons);
    }

    // Binary Search: find one occurrence, then expand to count duplicates
    public static void binarySearch(String[] logs, String target) {
        int low = 0, high = logs.length - 1;
        int comparisons = 0;
        int foundIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            int cmp = logs[mid].compareTo(target);
            if (cmp == 0) {
                foundIndex = mid;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary search for " + target + ":");
        if (foundIndex != -1) {
            // Count duplicates around foundIndex
            int count = 1;
            int left = foundIndex - 1;
            while (left >= 0 && logs[left].equals(target)) {
                count++;
                left--;
            }
            int right = foundIndex + 1;
            while (right < logs.length && logs[right].equals(target)) {
                count++;
                right++;
            }
            System.out.println("Found at index " + foundIndex);
            System.out.println("Total occurrences: " + count);
        } else {
            System.out.println("Not found");
        }
        System.out.println("Comparisons: " + comparisons);
    }

    public static void main(String[] args) {
        // Example transaction logs
        String[] logs = {"accB", "accA", "accB", "accC"};

        // Sort logs for binary search
        Arrays.sort(logs);
        System.out.println("Sorted logs: " + Arrays.toString(logs));

        // Linear search demo
        linearSearch(logs, "accB");

        // Binary search demo
        binarySearch(logs, "accB");
    }
}