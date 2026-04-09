import java.util.ArrayList;
import java.util.List;

class Transaction {
    String id;
    double fee;
    String timestamp; // simple string for demo, could be LocalTime

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ": " + fee + "@" + timestamp;
    }
}

public class Problem1 {

    // Bubble Sort (ascending by fee)
    public static void bubbleSort(List<Transaction> transactions) {
        int n = transactions.size();
        boolean swapped;
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    // swap
                    Transaction temp = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break; // early termination
        }
        System.out.println("BubbleSort completed: " + passes + " passes, " + swaps + " swaps");
    }

    // Insertion Sort (by fee, then timestamp for stability)
    public static void insertionSort(List<Transaction> transactions) {
        int n = transactions.size();
        for (int i = 1; i < n; i++) {
            Transaction key = transactions.get(i);
            int j = i - 1;
            while (j >= 0 && compare(transactions.get(j), key) > 0) {
                transactions.set(j + 1, transactions.get(j));
                j--;
            }
            transactions.set(j + 1, key);
        }
        System.out.println("InsertionSort completed.");
    }

    // Comparison: fee first, then timestamp
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee < t2.fee) return -1;
        if (t1.fee > t2.fee) return 1;
        return t1.timestamp.compareTo(t2.timestamp); // stable tie-breaker
    }

    // Outlier detection
    public static void detectOutliers(List<Transaction> transactions) {
        System.out.print("High-fee outliers: ");
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.fee > 50.0) {
                System.out.print(t + " ");
                found = true;
            }
        }
        if (!found) System.out.print("none");
        System.out.println();
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("Input transactions:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        // Bubble Sort demo
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        bubbleSort(bubbleList);
        System.out.println("BubbleSort (fees): " + bubbleList);

        // Insertion Sort demo
        List<Transaction> insertionList = new ArrayList<>(transactions);
        insertionSort(insertionList);
        System.out.println("InsertionSort (fee+ts): " + insertionList);

        // Outlier detection
        detectOutliers(transactions);
    }
}