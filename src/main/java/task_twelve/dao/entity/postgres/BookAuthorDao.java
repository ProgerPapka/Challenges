package task_twelve.dao.entity.postgres;

import org.apache.log4j.Logger;
import task_twelve.entity.EntityBookAuthor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDao {

    private static Logger logger = Logger.getLogger(BookAuthorDao.class);

    private Connection connection;

    public BookAuthorDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertBookAuthor(EntityBookAuthor bookAuthor) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO \"Books_Authors\" VALUES (?, ?)"
            );
            statement.setInt(1, bookAuthor.getIdBook());
            statement.setInt(2, bookAuthor.getIdAuthor());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    public List<EntityBookAuthor> selectByIdBook(int idBook) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"Books_Authors\" WHERE id_book = ?");
            statement.setInt(1, idBook);
            ResultSet rs = statement.executeQuery();
            List<EntityBookAuthor> bookAuthors = new ArrayList<>();
            while (rs.next()) {
                int idAuthor = rs.getInt("id_author");
                bookAuthors.add(new EntityBookAuthor(idBook, idAuthor));
            }
            return bookAuthors;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }


    public List<EntityBookAuthor> selectAll() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"Books_Authors\"");
            ResultSet rs = statement.executeQuery();
            List<EntityBookAuthor> bookAuthors = new ArrayList<>();
            while (rs.next()) {
                int idAuthor = rs.getInt("id_author");
                int idBook = rs.getInt("id_book");
                bookAuthors.add(new EntityBookAuthor(idBook, idAuthor));
            }
            return bookAuthors;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }
}
