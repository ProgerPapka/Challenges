package task_four.first.cache;

import task_four.first.annotation.CacheDeclaration;

import java.util.*;

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
