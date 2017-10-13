package task_four.first.bean;

import task_four.first.annotation.InjectCache;
import task_four.first.cache.Cache;
import task_four.first.pojo.Telephone;

public class TelephoneBean {

    @InjectCache(name = "ArrayListCache")
    Cache<Integer, String> cache;

    public void setTelephone(Telephone telephone) {
        cache.put(telephone.getPhone(), telephone.getNameOwner());
    }

    public Telephone getOwnerByNumberPhone(Integer numberPhone) {
        return new Telephone(numberPhone, cache.get(numberPhone));
    }

}
