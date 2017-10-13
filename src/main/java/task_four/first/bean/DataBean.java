package task_four.first.bean;

import task_four.first.pojo.Data;
import task_four.first.annotation.InjectCache;
import task_four.first.cache.Cache;

public class DataBean {

    @InjectCache(name = "HashMapCache")
    private Cache<Integer,String> cache;

    public void setData(Data data){
        cache.put(data.getId(),data.getDescription());
    }

    public Data getData(Integer key){
        return new Data(cache.get(key),key);
    }

}
