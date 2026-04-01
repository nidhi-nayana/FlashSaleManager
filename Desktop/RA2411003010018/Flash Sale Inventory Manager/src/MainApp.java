public class MainApp
{
    public static void main(String[] args)
    {
        InventoryService service = new InventoryService();

        String product = "IPHONE15_256GB";

        // Initial stock
        DataStore.stockMap.put(product, 5);

        // Check stock
        System.out.println("Stock: " + service.checkStock(product));

        // Simulate purchases
        for (int i = 1; i <= 8; i++)
        {
            String result = service.purchaseItem(product, i);
            System.out.println("User " + i + ": " + result);
        }

        // Restock
        System.out.println("\nRestocking...");
        service.restock(product, 3);

        // Final stock
        System.out.println("Final Stock: " + service.checkStock(product));
    }
}