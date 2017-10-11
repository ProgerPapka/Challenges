package task_two;

import challenge_one.Function;
import challenge_one.ReduceFunction;

import java.util.*;

public class ListWithPredicates<E> implements List<E> {

    private ArrayList<E> array;
    private Set<E> predicates;

    public ListWithPredicates(ArrayList<E> array, Set<E> predicates) {
        this.array = array;
        this.predicates = predicates;
    }


    public <M> ListWithPredicates<M> map(Function<E, M> fForArray, Function<E, M> fFroPredicate) {
        ArrayList<M> l = new ArrayList<M>();
        Set<M> p = new HashSet<>();
        for (E o : array) {
            l.add(fForArray.apply(o));
        }
        for (E o : predicates) {
            p.add(fFroPredicate.apply(o));
        }
        return new ListWithPredicates<M>(l, p);
    }

    public ListWithPredicates<E> filter(Function<E, Boolean> f) {
        ArrayList<E> l = new ArrayList<E>();
        for (E o : array) {
            if (f.apply(o)) {
                l.add(o);
            }
        }
        return new ListWithPredicates<E>(l, predicates);
    }

    public E reduce(ReduceFunction<E> f) {
        Iterator<E> iterator = array.iterator();
        E result = iterator.next();
        while (iterator.hasNext()) {
            result = f.apply(result, iterator.next());
        }
        return result;
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
                if (curEl == array.size())
                    return false;
                while (predicates.contains(array.get(curEl))) {
                    ++curEl;
                    if (curEl == array.size())
                        return false;
                }
                return curEl < array.size();
            }

            public E next() {
                if (curEl >= array.size())
                    throw new NoSuchElementException();

                return array.get(last = curEl++);
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
        }
    }
}
