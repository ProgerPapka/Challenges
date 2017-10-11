package task_four.cache;

import task_four.annotation.CacheDeclaration;

import java.util.HashMap;
import java.util.Map;

@CacheDeclaration(name = "HashMapCache")
public class HashMapCache implements Cache<Integer, String> {

    private Map<Integer, String> map = new HashMap<>();

    @Override
    public void put(Integer key, String value) {
        map.put(key, value);
    }

    @Override
    public String get(Integer key) {
        return map.get(key);
    }
}
