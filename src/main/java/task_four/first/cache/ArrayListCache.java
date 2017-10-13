package task_four.first.cache;

import task_four.first.annotation.CacheDeclaration;

import java.util.ArrayList;
import java.util.List;

@CacheDeclaration(name = "ArrayListCache")
public class ArrayListCache implements Cache<Integer, String> {

    private List<Item> list = new ArrayList<>();

    @Override
    public void put(Integer key, String value) {
        for(Item item : list){
            if(item.integer.equals(key)){
                return;
            }
        }
        list.add(new Item(value,key));
    }

    @Override
    public String get(Integer key) {
        for(Item item : list){
            if(item.integer.equals(key)){
                return item.string;
            }
        }
        return null;
    }

    private class Item {
        private String string;
        private Integer integer;

        private Item(String string, Integer integer) {
            this.string = string;
            this.integer = integer;
        }

    }

}
