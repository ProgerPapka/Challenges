package task_four.cache;

public interface Cache<Integer, String> {
    void put(Integer key, String value);
    String get(Integer key);
}
