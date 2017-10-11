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

            /*HashSet<Integer> set = new HashSet<>();
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
            }*/


        //Test3

        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> l = new ArrayList<>();
        {
            l.add(1);
            l.add(2);
            l.add(3);
            l.add(4);
            set.add(1);
            set.add(2);
        }
        List<Integer> list = new ListWithPredicates<>(l, set);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();
        {
            list.add(0, 5); // add 5 to position 0
            System.out.println("Would add 1?:  " + list.add(1)); // add 1
            list.add(6); // add 6
        }
        System.out.println();
        it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();
        it = list.iterator();
        list.remove((Integer)5); //remove 5
        System.out.println("Will remove list[0] = 1 ?:  " + list.remove((Integer) 1)); //remove 1
        System.out.println("Will remove list[1] = 2 ?:  " + list.remove((Integer) 2)); //remove 1
        while (it.hasNext()){
            System.out.println(it.next());
        }


    }

}
