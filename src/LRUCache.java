import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LRUCache {

    private final Queue<Integer> queue = new LinkedList<>();
    private final Map<Integer, Integer> map = new HashMap<>();
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            int value = map.get(key);
            queue.remove(key);
            queue.offer(key);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            queue.remove(key);
        }
        else if(queue.size() == capacity){
            Integer oldestKey = queue.poll();
            map.remove(oldestKey);
        }
        queue.offer(key);
        map.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);  //2 removed
        System.out.println(cache.get(2)); //-1
        cache.put(4, 4); // 3 removed
        System.out.println(cache.get(1)); //-1
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}