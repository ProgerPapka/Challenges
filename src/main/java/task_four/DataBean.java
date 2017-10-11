package task_four;

import task_four.annotation.InjectCache;
import task_four.cache.Cache;

public class DataBean {
    @InjectCache(name = "HashMapCache")
    private Cache cache;
}
