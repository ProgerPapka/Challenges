package task_four.first.test;

import task_four.first.annotation.InjectCache;
import task_four.first.cache.Cache;

public class FirstTestBean {

    @InjectCache(name = "ListCache")
    private Cache<Integer,String> cache;

    public String getData(Integer key){
        return cache.get(key);
    }

}
