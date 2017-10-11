package task_two;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
            //Test1

            /*HashSet<Integer> set = new HashSet<>();
            ArrayList<Integer> l = new ArrayList<>();
            l.add(5);
            l.add(6);
            l.add(6);
            l.add(7);
            set.add(6);
            set.add(7);
            List<Integer> list = new ListWithPredicates<>(l, set);
            boolean a = list.add(11);
            boolean b = list.add(2);
            Iterator it = list.iterator();
            it.next();
            it.remove();
            while (it.hasNext()) {
                    System.out.println(it.next());
            }*/



            //Test2

            HashSet<Integer> set = new HashSet<>();
            ArrayList<Integer> l = new ArrayList<>();
            ArrayList<Integer> qq = new ArrayList<>();
            {
                    qq.add(22);
                    qq.add(24);
                    qq.add(23);
            }
            l.add(1);
            l.add(2);
            l.add(3);
            l.add(4);
            set.add(2);
            set.add(3);
            List<Integer> list = new ListWithPredicates<>(l, set);
            boolean a = list.add(11);
            boolean b = list.add(6);
            list.addAll(5,qq);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
            System.out.println();
            list.removeAll(qq);
            it = list.iterator();
            while (it.hasNext()) {
                    System.out.println(it.next());
            }


            //Test3

    }

}
