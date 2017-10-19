package task_four.first.test;

import task_four.first.cache.Cache;
import task_four.first.data.FillingCache;
import task_four.first.exception.ReflectionException;
import task_four.first.injector.CacheInjector;
import task_four.first.loader.CacheLoader;

import java.util.Map;

public class Test {

    public void processTest() {
        CacheLoader cacheLoader = new CacheLoader();
        try {
            Map<String,Cache> map = cacheLoader
                    .getAllCache("task_four.first.cache.implementation");
            new FillingCache().fill(map);
            CacheInjector  injector = new CacheInjector(map);
            FirstTestBean firstTestBean = new FirstTestBean();
            SecondTestBean secondTestBean = new Bean();
            injector.inject(firstTestBean);
            injector.inject(secondTestBean);
            System.out.println(firstTestBean.getData(2));
            System.out.println(secondTestBean.getData(20));
        } catch (ReflectionException e) {
            System.out.println(e.getMessage());
        }
    }

}
