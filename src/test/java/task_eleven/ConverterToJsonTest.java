package task_eleven;

import org.junit.BeforeClass;
import org.junit.Test;
import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ConverterToJsonTest {

    private static List<Publisher> publishers;

    @BeforeClass
    public static void init() {
        List<Author> authors = Arrays.asList(
                new Author("Дмитрий",
                        LocalDate.of(1994, 8, 12),
                        Author.Sex.MALE),
                new Author("Мария",
                        LocalDate.of(1920, 7, 11),
                        LocalDate.of(2002, 11, 1),
                        Author.Sex.FEMALE)
        );
        List<Book> books = Arrays.asList(
                new Book("Стратус",
                        LocalDate.of(1955, 4, 1),
                        Collections.singletonList(authors.get(1))),
                new Book("Шантара",
                        LocalDate.of(2006, 11, 16),
                        Collections.singletonList(authors.get(0))),
                new Book("Хаксли",
                        LocalDate.of(2002, 1, 1),
                        Arrays.asList(authors.get(0), authors.get(1))
                )

        );
        publishers = new ArrayList<>();
        publishers.add(new Publisher("Асгард", books));
        publishers.add(new Publisher("Азбука", Arrays.asList(
                books.get(1),
                books.get(0)
        )));
    }

    @Test
    public void convertPublishersToJSON() throws Exception {
        File file = new File("D:\\json\\publishers.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        ConverterToJson converter = new ConverterToJson();
        assertTrue(converter.convertPublishersToJSON(publishers, file));
    }

}