package challenge1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListWrapper<T> {

    private List<T> list;

    public void println(){
        for(T o:list){
            System.out.printf(o.toString()+ "\t");
        }
    }

    public ListWrapper<T> map(Function<T,T> f){
        List<T> l = new ArrayList<T>();

        for (T o:list){
            l.add(f.apply(o));
        }
        return new ListWrapper<T>(l);
    }


    public ListWrapper<T> filter(Function<T,Boolean> f){
        List<T> l = new ArrayList<T>();

        for (T o:list){
            if(f.apply(o)){
                l.add(o);
            }
        }
        return new ListWrapper<T>(l);
    }

    public ListWrapper(List<T> list) {
        this.list = list;
    }


    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1,2,3,4,5,6);

        ListWrapper<Integer> lw = new ListWrapper<Integer>(l);

        lw.map(new Function<Integer,Integer>() {
            public Integer apply(Integer o) {
                return o*3;
            }
        })
                .filter(new Function<Integer, Boolean>() {
            public Boolean apply(Integer o) {
                return o%2==0;
            }
        }).println();


    }

}
