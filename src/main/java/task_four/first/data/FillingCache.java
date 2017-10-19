package task_four.first.data;

import task_four.first.cache.Cache;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class FillingCache {
    public void fill(Map<String, Cache> map) {
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Cache cache = map.get(key);
            cache.put(12, "Test value 1 " + cache.getClass().getSimpleName());
            cache.put(2, "Test value 2 " + cache.getClass().getSimpleName());
            cache.put(22, "Test value 3 " + cache.getClass().getSimpleName());
            cache.put(20, "Test value 4 " + cache.getClass().getSimpleName());
        }
    }
}
