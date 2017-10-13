package task_four.first;

import task_four.first.annotation.CacheDeclaration;
import task_four.first.annotation.InjectCache;
import task_four.first.cache.Cache;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CacheInjector {

    private List<Class<? extends Cache>> listCache = new ArrayList<>();

    public CacheInjector() {
        getAllCache();
    }

    public void inject(Object bean) {
        Class beanClass = bean.getClass();
        Field[] beanFields;
        do {
            beanFields = beanClass.getDeclaredFields();
            for (Field f : beanFields) {
                f.setAccessible(true);
                Annotation annotation = f.getAnnotation(InjectCache.class);
                if (annotation == null) {
                    continue;
                }
                String nameCache = ((InjectCache) annotation).name();
                Class<? extends Cache> cacheInject = null;
                for (Class<? extends Cache> cacheClass : listCache) {
                    Annotation a = cacheClass
                            .getAnnotation(CacheDeclaration.class);
                    if (nameCache.equals(((CacheDeclaration) a).name())) {
                        cacheInject = cacheClass;
                        break;
                    }
                }
                try {
                    if (cacheInject == null) {
                        throw new ClassNotFoundException("Error in the field "
                                + f.getName() + ". Don't find cache with name "
                                + nameCache + ".");
                    }
                    Cache cache = cacheInject.newInstance();
                    f.set(bean, cache);
                } catch (InstantiationException | IllegalAccessException
                        | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            beanClass = beanClass.getSuperclass();
        } while (beanClass != Object.class);

    }

    private void getAllCache() {
        File file = new File("src/main/java/task_four/first/cache/");
        File[] files = file.listFiles();
        List<String> nameFiles = new ArrayList<>();
        assert files != null;
        if (files.length == 0) {
            return;
        }
        for (File f : files) {
            nameFiles.add(f.toString()
                    .replace("src\\main\\java\\", "")
                    .replace(".java", "")
                    .replace("\\", "."));
        }
        for (String cache : nameFiles) {
            try {
                listCache.add((Class<? extends Cache>) Class.forName(cache));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        listCache.removeIf(cache -> cache.getAnnotation(CacheDeclaration.class) == null);
    }

}
