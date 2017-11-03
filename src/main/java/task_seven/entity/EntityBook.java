package task_seven.entity;

import task_seven.representation.EntityBookAsString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EntityBook implements Serializable, EntityBookAsString {

    private int id;
    private final String name;
    private final String releaseDate;
    private List<String> listIdAuthors;

    public EntityBook(int id, String name, String releaseDate) {
        this(id,name,releaseDate,new ArrayList<>());
    }

    public EntityBook(String name, String releaseDate) {
        this(0,name,releaseDate,new ArrayList<>());
    }

    public EntityBook(int id, String name, String releaseDate, List<String> listIdAuthors) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.listIdAuthors = listIdAuthors;
    }

    public List<String> getListIdAuthors() {
        return listIdAuthors;
    }

    public void setListIdAuthors(List<String> listIdAuthors) {
        this.listIdAuthors = listIdAuthors;
    }

    public boolean containsAuthor(String id){
        return listIdAuthors.contains(id);
    }

    public void addIdAuthor(String id) {
        listIdAuthors.add(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityBook that = (EntityBook) o;

        if (!name.equals(that.name)) return false;
        if (!releaseDate.equals(that.releaseDate)) return false;
        return listIdAuthors.equals(that.listIdAuthors);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + listIdAuthors.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EntityBook{" +
                "name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", listIdAuthors=" + listIdAuthors +
                '}';
    }

    @Override
    public String valueAsString() {
        StringBuilder res = new StringBuilder();
        res.append(START_BOOK).append("\n\t");
        res.append(ID).append(EQUALITY).append(id).append("\n\t");
        res.append(NAME).append(EQUALITY).append(name).append("\n\t");
        res.append(RELEASE_DAY).append(EQUALITY).append(releaseDate).append("\n\t");
        res.append(START_LIST_ID_AUTHORS).append(START_LIST_ID);
        for (String id : listIdAuthors) {
            res.append("\n\t\t").append(id);
        }
        res.append("\n\t").append(END_LIST_ID_AUTHORS).append('\n');
        res.append(END_BOOK);
        return res.toString();
    }
}
