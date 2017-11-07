package task_twelve;

import org.apache.log4j.Logger;
import task_four.second.domain.Author;
import task_four.second.domain.Book;
import task_four.second.domain.Publisher;
import task_twelve.dao.object.PublishersDao;
import task_twelve.exception.DataBaseException;
import task_twelve.util.DataBaseUtil;
import task_twelve.util.DeleteDataBase;
import task_twelve.util.InitDataBase;
import task_twelve.util.PostgresDBUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PostgresTest {

    private static Logger logger = Logger.getLogger(PostgresTest.class);

    private List<Publisher> publishers;
    private List<Author> authors;
    private List<Book> books;

    public PostgresTest() {
        initData();
    }

    public void process(boolean createDB) {
        if(createDB){
            try {
                DataBaseUtil root = new PostgresDBUtil(
                        "src\\main\\resources\\rootpg.properties");
                InitDataBase initDataBase = new InitDataBase(
                        root.getConnection());
                DeleteDataBase deleteDataBase = new DeleteDataBase(
                        root.getConnection());
                deleteDataBase.dropDB();
                initDataBase.createUser();
                initDataBase.createDataBase();
                root.closeConnection();
                DataBaseUtil util = new PostgresDBUtil();
                initDataBase = new InitDataBase(util.getConnection());
                initDataBase.createTables();
                util.closeConnection();
            } catch (DataBaseException e) {
                logger.error(e);
            }
        }
        try {
            DataBaseUtil util = new PostgresDBUtil();
            PublishersDao dao = new PublishersDao(util.getConnection());
            dao.setToBD(publishers);
            List<Publisher> list = dao.getFromDB();
            logger.info(list.toString());
            util.closeConnection();
        } catch (DataBaseException e) {
            logger.error(e);
        }
    }

    private void initData() {
        authors = Arrays.asList(
                new Author("James",
                        LocalDate.of(1995, 8, 12),
                        Author.Sex.MALE),
                new Author("Sue",
                        LocalDate.of(1920, 7, 11),
                        LocalDate.of(2002, 1, 1),
                        Author.Sex.FEMALE)
        );
        books = Arrays.asList(
                new Book("Secret",
                        LocalDate.of(1955, 4, 1),
                        Collections.singletonList(authors.get(1))),
                new Book("Ellion",
                        LocalDate.of(2006, 11, 16),
                        Collections.singletonList(authors.get(0)))
        );
        publishers = new ArrayList<>();
        publishers.add(new Publisher("World", books));
    }

}
