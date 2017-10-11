package challenge1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListWrapper<T> {

    private List<T> list;

    public void println() {
        for (T o : list) {
            System.out.printf(o.toString() + "\t");
        }
    }

    public <M> ListWrapper<M> map(Function<T, M> f) {
        List<M> l = new ArrayList<M>();

        for (T o : list) {
            l.add(f.apply(o));
        }
        return new ListWrapper<M>(l);
    }


    public ListWrapper<T> filter(Function<T, Boolean> f) {
        List<T> l = new ArrayList<T>();

        for (T o : list) {
            if (f.apply(o)) {
                l.add(o);
            }
        }
        return new ListWrapper<T>(l);
    }

    public T reduce(ReduceFunction<T> f) {
        Iterator<T> iterator = list.iterator();
        T result = iterator.next();
        while (iterator.hasNext()) {
            result = f.apply(result, iterator.next());
        }
        return result;
    }


    public ListWrapper(List<T> list) {
        this.list = list;
    }


    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6);

        ListWrapper<Integer> lw = new ListWrapper<Integer>(l);

        lw.map(new Function<Integer, String>() {
            public String apply(Integer o) {
                return "\"" + o.toString() + "\"";
            }
        }).println();
        System.out.println();
        lw.map(new Function<Integer, Integer>() {
            public Integer apply(Integer o) {
                return o * 3;
            }
        })
                .filter(new Function<Integer, Boolean>() {
                    public Boolean apply(Integer o) {
                        return o % 2 == 0;
                    }
                }).println();
        System.out.println();
        Integer qwerty = lw.reduce(new ReduceFunction<Integer>() {
            @Override
            public Integer apply(Integer o1, Integer o2) {
                return o1 * o2;
            }
        });
        System.out.println(qwerty);

    }

}
