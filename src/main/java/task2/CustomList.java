package task2;

import java.util.*;

import static java.util.Arrays.asList;

public class CustomList<E> implements List<E> {

    private ArrayList<E> array;
    private Set<E> predicates;

    public CustomList(ArrayList<E> array, Set<E> predicates) {
        this.array = array;
        this.predicates = predicates;
    }

    public int size() {
        return array.size();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    public boolean contains(Object o) {
        return array.contains(o);
    }

    public boolean add(E e) {
        return !predicates.contains(e) && array.add(e);
    }

    public Object[] toArray() {
        return array.toArray();
    }

    public boolean remove(Object o) {
        return !predicates.contains(o) && array.remove(o);
    }

    public <T> T[] toArray(T[] a) {
        return array.toArray(a);
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int curEl = 0;
            private int last = -1;

            public boolean hasNext() {
                return curEl < array.size();
            }

            public E next() {
                if (curEl >= array.size())
                    throw new NoSuchElementException();
                E e = array.get(curEl);
                last = curEl++;
                if (predicates.contains(e))
                    return this.next();
                else
                    return e;
            }

            public void remove() {
                if (last == -1)
                    throw new IllegalStateException();
                array.remove(last);
                curEl--;
                last--;
            }
        };
    }

    public boolean containsAll(Collection<?> c) {
        return array.containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c) {
        return array.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return array.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return array.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return array.retainAll(c);
    }

    public void clear() {
        array.clear();
    }


    public E get(int index) {
        return array.get(index);
    }

    public E set(int index, E element) {
        return set(index, element);
    }

    public void add(int index, E element) {
        array.add(index, element);
    }

    public E remove(int index) {
        return array.remove(index);
    }

    public int indexOf(Object o) {
        return array.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return array.lastIndexOf(o);
    }

    public ListIterator<E> listIterator() {
        return array.listIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return array.listIterator(index);
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return array.subList(fromIndex, toIndex);
    }

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> l = new ArrayList<>();
        l.add(22);
        l.add(1);
        l.add(2);
        l.add(3);
        set.add(1);
        set.add(2);
        List<Integer> list = new CustomList<>(l,set);
        boolean a = list.add(11);
        boolean b = list.add(2);
        Iterator it = list.iterator();
        it.next();
        it.remove();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
