package task_four.first.injector;

import task_four.first.annotation.InjectCache;
import task_four.first.cache.Cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public class CacheInjector {

    private Map<String, Cache> mapCache;

    public CacheInjector(Map<String, Cache> listCache) {
        this.mapCache = listCache;
    }

    public void inject(Object bean) {
        Class beanClass = bean.getClass();
        Field[] beanFields;
        do {
            beanFields = beanClass.getDeclaredFields();
            for (Field f : beanFields) {
                f.setAccessible(true);
                Annotation annotation = f.getDeclaredAnnotation(InjectCache.class);
                if (annotation == null) {
                    continue;
                }
                String nameCache = ((InjectCache) annotation).name();
                Cache cache = mapCache.get(nameCache);
                if (cache == null) {
                    try {
                        throw new ClassNotFoundException("Error in the field "
                                + f.getName() + ". Don't find cache with name "
                                + nameCache + ".");
                    } catch (ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }
                try {
                    f.set(bean,cache);
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
            beanClass = beanClass.getSuperclass();
        } while (beanClass != Object.class);

    }


}
