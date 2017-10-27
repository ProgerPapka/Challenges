package task_four.second.entity;

import task_four.second.domain.Author;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class EntityBook implements Serializable{

    private String name;
    private String releaseDate;
    private List<EntityAuthor> authors;
    private List<String> listIdAuthors;

    public EntityBook(String name, String releaseDate, List<EntityAuthor> authors) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.authors = authors;
    }

    public EntityBook(String name, String releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.authors = new ArrayList<>();
    }

    public List<String> getListIdAuthors() {
        return listIdAuthors;
    }

    public void setListIdAuthors(List<String> listIdAuthors) {
        this.listIdAuthors = listIdAuthors;
    }

    public void setAuthors(List<EntityAuthor> authors) {
        this.authors = authors;
    }

    public void addAuthor(EntityAuthor a){
        authors.add(a);
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<EntityAuthor> getAuthors() {
        return authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityBook that = (EntityBook) o;

        if (!name.equals(that.name)) return false;
        if (!releaseDate.equals(that.releaseDate)) return false;
        return authors.equals(that.authors);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + authors.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Book\n\t");
        res.append("id = ");
        res.append(hashCode());
        res.append("\n\t");
        res.append("name = ");
        res.append(name);
        res.append("\n\t");
        res.append("releaseDate = ");
        res.append(releaseDate);
        res.append("\n\t");
        res.append("ListIdAuthors { ");
        for (EntityAuthor a: new HashSet<>(authors)) {
            res.append("\n\t\t");
            res.append(a.hashCode());
        }
        res.append("\n\t");
        res.append("}");
        res.append('\n');
        res.append("endBook");
        for (EntityAuthor a: authors){
            res.append('\n');
            res.append(a.toString());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        EntityAuthor a = new EntityAuthor("Anna",
                LocalDate.of(1911, 3, 13).toString(),
                LocalDate.of(1988, 11, 22).toString(),
                Author.Sex.FEMALE.name());
        EntityAuthor b = new EntityAuthor("Masha",
                LocalDate.of(1931, 3, 13).toString(),
                LocalDate.of(1998, 1, 21).toString(),
                Author.Sex.FEMALE.name());
        List<EntityAuthor> aa = Arrays.asList(a,b);
        EntityBook book = new EntityBook(
                "BigBan",LocalDate.now().toString(),aa
        );
        System.out.println(book.toString());
    }
}
