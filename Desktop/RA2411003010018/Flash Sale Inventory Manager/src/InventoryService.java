import java.util.Queue;

public class InventoryService
{
    // Check stock
    public int checkStock(String productId)
    {
        return DataStore.stockMap.getOrDefault(productId, 0);
    }

    // Purchase item (THREAD-SAFE)
    public synchronized String purchaseItem(String productId, int userId)
    {
        int stock = DataStore.stockMap.getOrDefault(productId, 0);

        if (stock > 0)
        {
            // Reduce stock
            DataStore.stockMap.put(productId, stock - 1);

            return "Success! Remaining stock: " + (stock - 1);
        }
        else
        {
            // Add to waiting list
            DataStore.waitingList.putIfAbsent(productId, new java.util.concurrent.ConcurrentLinkedQueue<>());

            Queue<Integer> queue = DataStore.waitingList.get(productId);
            queue.add(userId);

            return "Out of stock! Added to waiting list. Position: " + queue.size();
        }
    }

    // Restock product
    public synchronized void restock(String productId, int quantity)
    {
        int currentStock = DataStore.stockMap.getOrDefault(productId, 0);
        DataStore.stockMap.put(productId, currentStock + quantity);

        // Serve waiting list
        if (DataStore.waitingList.containsKey(productId))
        {
            Queue<Integer> queue = DataStore.waitingList.get(productId);

            while (!queue.isEmpty() && DataStore.stockMap.get(productId) > 0)
            {
                int userId = queue.poll();
                int stock = DataStore.stockMap.get(productId);

                DataStore.stockMap.put(productId, stock - 1);

                System.out.println("User " + userId + " got product from waiting list!");
            }
        }
    }
}