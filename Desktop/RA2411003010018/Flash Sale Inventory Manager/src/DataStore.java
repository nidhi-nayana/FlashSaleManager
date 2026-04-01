import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataStore
{
    // productId -> stock
    public static ConcurrentHashMap<String, Integer> stockMap = new ConcurrentHashMap<>();

    // productId -> waiting users (FIFO)
    public static ConcurrentHashMap<String, ConcurrentLinkedQueue<Integer>> waitingList = new ConcurrentHashMap<>();
}