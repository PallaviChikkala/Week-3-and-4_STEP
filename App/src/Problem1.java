import java.util.Arrays;
import java.util.Random;

class Asset {
    String name;
    double returnRate;   // percentage return
    double volatility;   // risk measure

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "% (vol=" + volatility + ")";
    }
}

public class Problem1 {

    // Merge Sort (ascending by returnRate, stable)
    public static void mergeSort(Asset[] assets, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(assets, left, mid);
            mergeSort(assets, mid + 1, right);
            merge(assets, left, mid, right);
        }
    }

    private static void merge(Asset[] assets, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = assets[left + i];
        for (int j = 0; j < n2; j++) R[j] = assets[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                assets[k++] = L[i++];
            } else {
                assets[k++] = R[j++];
            }
        }
        while (i < n1) assets[k++] = L[i++];
        while (j < n2) assets[k++] = R[j++];
    }

    // Quick Sort (descending by returnRate, then volatility ascending)
    public static void quickSort(Asset[] assets, int low, int high, boolean randomPivot) {
        if (low < high) {
            int pi = partition(assets, low, high, randomPivot);
            quickSort(assets, low, pi - 1, randomPivot);
            quickSort(assets, pi + 1, high, randomPivot);
        }
    }

    private static int partition(Asset[] assets, int low, int high, boolean randomPivot) {
        int pivotIndex = high;
        if (randomPivot) {
            pivotIndex = low + new Random().nextInt(high - low + 1);
        }
        // median-of-three pivot selection
        else {
            int mid = (low + high) / 2;
            if (assets[low].returnRate > assets[mid].returnRate) swap(assets, low, mid);
            if (assets[low].returnRate > assets[high].returnRate) swap(assets, low, high);
            if (assets[mid].returnRate > assets[high].returnRate) swap(assets, mid, high);
            pivotIndex = mid;
        }

        Asset pivot = assets[pivotIndex];
        swap(assets, pivotIndex, high);

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compare(assets[j], pivot) > 0) { // DESC return, ASC volatility
                i++;
                swap(assets, i, j);
            }
        }
        swap(assets, i + 1, high);
        return i + 1;
    }

    private static void swap(Asset[] assets, int i, int j) {
        Asset temp = assets[i];
        assets[i] = assets[j];
        assets[j] = temp;
    }

    // Comparison: returnRate DESC, volatility ASC
    private static int compare(Asset a1, Asset a2) {
        if (a1.returnRate != a2.returnRate) {
            return Double.compare(a1.returnRate, a2.returnRate);
        }
        return Double.compare(a2.volatility, a1.volatility); // lower volatility preferred
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12.0, 0.25),
                new Asset("TSLA", 8.0, 0.40),
                new Asset("GOOG", 15.0, 0.20)
        };

        System.out.println("Input: " + Arrays.toString(assets));

        // Merge Sort demo (ascending by returnRate)
        Asset[] mergeSorted = assets.clone();
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        System.out.println("MergeSort (asc): " + Arrays.toString(mergeSorted));

        // Quick Sort demo (descending by returnRate, volatility ASC)
        Asset[] quickSorted = assets.clone();
        quickSort(quickSorted, 0, quickSorted.length - 1, false); // median-of-three pivot
        System.out.println("QuickSort (desc): " + Arrays.toString(quickSorted));
    }
}