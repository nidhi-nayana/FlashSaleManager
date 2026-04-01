import java.util.*;
class Transaction {
    String id;
    double fee;
    String timestamp; // HH:mm format

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

class TransactionSorter {

    public static void bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("Bubble Sort → Passes: " + passes + ", Swaps: " + swaps);
    }

    public static void insertionSortByFeeAndTime(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j)); // shift
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort completed.");
    }

    // Comparator: fee first, then timestamp
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    public static List<Transaction> findOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }
        return outliers;
    }
    public static void sortTransactions(List<Transaction> list) {
        int size = list.size();

        if (size <= 100) {
            System.out.println("\nUsing Bubble Sort (Small Batch)");
            bubbleSortByFee(list);
        } else if (size <= 1000) {
            System.out.println("\nUsing Insertion Sort (Medium Batch)");
            insertionSortByFeeAndTime(list);
        } else {
            System.out.println("\nLarge dataset detected (>1000). Consider advanced sort.");
            insertionSortByFeeAndTime(list); // fallback
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));
        transactions.add(new Transaction("id4", 75.0, "11:00")); // outlier
        transactions.add(new Transaction("id5", 25.0, "08:45")); // duplicate fee

        System.out.println("Original Transactions:");
        System.out.println(transactions);

        sortTransactions(transactions);

        System.out.println("\nSorted Transactions:");
        System.out.println(transactions);

        List<Transaction> outliers = findOutliers(transactions);

        System.out.println("\nHigh-Fee Outliers (>50):");
        if (outliers.isEmpty()) {
            System.out.println("None");
        } else {
            for (Transaction t : outliers) {
                System.out.println(t);
            }
        }
    }
}

