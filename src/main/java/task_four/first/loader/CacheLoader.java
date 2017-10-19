package task_four.first.loader;

import task_four.first.annotation.CacheDeclaration;
import task_four.first.cache.Cache;
import task_four.first.exception.ReflectionException;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CacheLoader {
    public Map<String, Cache> getAllCache(String cachePackage)
            throws ReflectionException {
        Map<String, Cache> map = new HashMap<>();
        URL path = CacheLoader.class.getClassLoader()
                .getResource(cachePackage.replace(".", "/"));
        if (path == null) {
            throw new ReflectionException("This package doesn't exist!");
        }
        File file = new File(path.getFile());
        File[] files = file.listFiles();
        if (files == null) {
            throw new ReflectionException("This package doesn't have files");
        }
        for (File f : files) {
            String name = f.getName().replace(".class", "");
            Class<Cache> cacheClass = null;
            try {
                cacheClass = (Class<Cache>)
                        Class.forName(cachePackage + "." + name);
            } catch (ClassNotFoundException e) {
                System.out.println("Class didn't found!\n" + e.getMessage());
                continue;
            }
            CacheDeclaration c = cacheClass.getAnnotation(CacheDeclaration.class);
            if (c == null) {
                continue;
            }
            try {
                map.put(c.name(), cacheClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        return map;
    }
}
