package task_seven.entity;

import task_four.second.domain.Author;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityPublisher implements Serializable {

    private String name;
    private List<EntityBook> books;

    public EntityPublisher(String name, List<EntityBook> books) {
        this.name = name;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public List<EntityBook> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityPublisher that = (EntityPublisher) o;

        if (!name.equals(that.name)) return false;
        return books.equals(that.books);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + books.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        HashSet<EntityBook> setBooks = new HashSet<>(books);
        Set<EntityAuthor> setAuthors = books
                .stream()
                .flatMap(a -> a.getAuthors().stream())
                .collect(Collectors.toSet());
        res.append("Publisher\n\t");
        res.append("id = ");
        res.append(hashCode());
        res.append("\n\t");
        res.append("name = ");
        res.append(name);
        res.append("\n\t");
        res.append("ListIdBooks {");
        for (EntityBook book : setBooks) {
            res.append("\n\t\t");
            res.append(book.hashCode());
        }
        res.append("\n\t");
        res.append("}\n");
        res.append("endPublisher");
        for (EntityBook book : setBooks) {
            res.append("\n");
            res.append("Book\n\t");
            res.append("id = ");
            res.append(book.hashCode());
            res.append("\n\t");
            res.append("name = ");
            res.append(book.getName());
            res.append("\n\t");
            res.append("releaseDate = ");
            res.append(book.getReleaseDate());
            res.append("\n\t");
            res.append("ListIdAuthors { ");
            for (EntityAuthor a : new HashSet<>(book.getAuthors())) {
                res.append("\n\t\t");
                res.append(a.hashCode());
            }
            res.append("\n\t");
            res.append("}");
            res.append('\n');
            res.append("endBook");
        }
        for (EntityAuthor author : setAuthors){
            res.append("\n");
            res.append(author.toString());
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
        List<EntityAuthor> aa = Arrays.asList(a, b);
        EntityBook bo = new EntityBook(
                "BigBan", LocalDate.now().toString(), aa
        );
        EntityBook boo = new EntityBook(
                "BigBan", LocalDate.now().toString(), aa
        );
        List<EntityBook> q = Arrays.asList(bo, boo);
        EntityPublisher w = new EntityPublisher(
                "Scrabble", q
        );
        System.out.println(w.toString());
    }
}
