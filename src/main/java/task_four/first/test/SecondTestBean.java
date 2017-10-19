package task_four.first.test;

import task_four.first.annotation.InjectCache;
import task_four.first.cache.Cache;

public class SecondTestBean {

    @InjectCache(name = "MapCache")
    private Cache<Integer,String> cache;

    public String getData(Integer key){
        return cache.get(key);
    }
}

class Bean extends SecondTestBean{

    @Override
    public String getData(Integer key) {
        return super.getData(key);
    }
}
