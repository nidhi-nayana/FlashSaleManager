import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore + " (Bal:" + accountBalance + ")";
    }
}

class ClientRiskSorter {

    // 🔁 Bubble Sort (Ascending by riskScore with visualization)
    public static void bubbleSortAscending(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            System.out.println("Pass " + (i + 1) + ":");

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // Swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // Visualize swap
                    System.out.println("  Swapped " + arr[j].name + " and " + arr[j + 1].name);
                }
            }

            if (!swapped) break; // Early termination
        }

        System.out.println("Total swaps: " + swaps);
    }

    // 🔁 Insertion Sort (Descending by riskScore, then by accountBalance)
    public static void insertionSortDescending(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j]; // shift right
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort (Descending) completed.");
    }

    // Comparator: risk DESC, then balance DESC
    private static int compare(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c1.riskScore, c2.riskScore);
        }
        return Double.compare(c1.accountBalance, c2.accountBalance);
    }

    // 🔝 Get Top N high-risk clients
    public static List<Client> getTopN(Client[] arr, int n) {
        List<Client> top = new ArrayList<>();
        for (int i = 0; i < Math.min(n, arr.length); i++) {
            top.add(arr[i]);
        }
        return top;
    }

    // 🧪 Main Method
    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 10000),
                new Client("clientB", 50, 7000),
                new Client("clientD", 80, 12000), // same risk, higher balance
                new Client("clientE", 30, 4000)
        };

        // Copy array for separate sorts
        Client[] bubbleArray = Arrays.copyOf(clients, clients.length);
        Client[] insertionArray = Arrays.copyOf(clients, clients.length);

        // 🔹 Bubble Sort (Ascending)
        System.out.println("Original:");
        System.out.println(Arrays.toString(bubbleArray));

        System.out.println("\nBubble Sort (Ascending by Risk):");
        bubbleSortAscending(bubbleArray);
        System.out.println("Sorted: " + Arrays.toString(bubbleArray));

        // 🔹 Insertion Sort (Descending)
        System.out.println("\nInsertion Sort (Descending by Risk + Balance):");
        insertionSortDescending(insertionArray);
        System.out.println("Sorted: " + Arrays.toString(insertionArray));

        // 🔝 Top 3 highest risk clients
        List<Client> topClients = getTopN(insertionArray, 3);

        System.out.println("\nTop 3 High-Risk Clients:");
        for (Client c : topClients) {
            System.out.println(c.name + " (" + c.riskScore + ")");
        }
    }
}

