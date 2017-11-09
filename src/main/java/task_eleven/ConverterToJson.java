package task_eleven;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ConverterToJson {

    private Logger logger = Logger.getLogger(ConverterToJson.class);
    private ObjectMapper mapper = new ObjectMapper();

    public boolean convertAuthorToJSON(Author author, File file) {
        return writeObject(author, file);
    }

    public boolean convertBookToJSON(Book book, File file) {
        return writeObject(book, file);
    }

    public boolean convertPublisherToJSON(Publisher publisher, File file) {
        return writeObject(publisher, file);
    }

    public boolean convertPublishersToJSON(List<Publisher> publishers,
                                           File file) {
        return writeObject(publishers, file);
    }

    private boolean writeObject(Object o, File file) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file))) {
            String jsonInString = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(o);
            writer.print(jsonInString);
            return true;
        } catch (IOException e) {
            logger.error(e);
            return false;
        }
    }

}
