package task_seven.entity;

import task_seven.representation.EntityPublisherAsString;

import java.io.Serializable;
import java.util.*;

public class EntityPublisher implements Serializable, EntityPublisherAsString {

    private final int id;
    private final String name;
    private List<String> listIdBooks;

    public EntityPublisher(int id, String name) {
        this(id, name, new ArrayList<>());
    }

    public EntityPublisher(int id, String name, List<String> listIdBooks) {
        this.id = id;
        this.name = name;
        this.listIdBooks = listIdBooks;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<String> getListIdBooks() {
        return listIdBooks;
    }

    public void setListIdBooks(List<String> listIdBooks) {
        this.listIdBooks = listIdBooks;
    }

    public boolean containsBook(String id){
        return listIdBooks.contains(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityPublisher that = (EntityPublisher) o;

        if (!name.equals(that.name)) return false;
        return listIdBooks.equals(that.listIdBooks);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + listIdBooks.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EntityPublisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listIdBooks=" + listIdBooks +
                '}';
    }

    @Override
    public String valueAsString() {
        StringBuilder res = new StringBuilder();
        res.append(START_PUBLISHER).append("\n\t");
        res.append(ID).append(EQUALITY).append(id).append("\n\t");
        res.append(NAME).append(EQUALITY).append(name).append("\n\t");
        res.append(START_LIST_ID_BOOKS).append(START_LIST_ID);
        for (String id : listIdBooks) {
            res.append("\n\t\t").append(id);
        }
        res.append("\n\t").append(END_LIST_ID_BOOKS).append("\n");
        res.append(END_PUBLISHER);
        return res.toString();
    }
}
