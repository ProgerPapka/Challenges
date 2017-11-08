package task_twelve.dao.entity.postgres;

import org.apache.log4j.Logger;
import task_twelve.entity.EntityBookAuthor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookAuthorDao {

    private static Logger logger = Logger.getLogger(BookAuthorDao.class);

    public boolean insert(EntityBookAuthor bookAuthor, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO Book_Author VALUES (?, ?)"
            );
            statement.setInt(1, bookAuthor.getIdBook());
            statement.setInt(2, bookAuthor.getIdAuthor());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
    }

    public List<EntityBookAuthor> selectByIdBook(int idBook, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT book_id, author_id FROM Book_Author WHERE book_id = ?");
            statement.setInt(1, idBook);
            ResultSet rs = statement.executeQuery();
            List<EntityBookAuthor> bookAuthors = new ArrayList<>();
            while (rs.next()) {
                int idAuthor = rs.getInt("author_id");
                bookAuthors.add(new EntityBookAuthor(idBook, idAuthor));
            }
            return bookAuthors;
        } catch (SQLException e) {
            logger.error(e);
            return Collections.emptyList();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
    }


    public List<EntityBookAuthor> selectAll(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT book_id, author_id FROM Book_Author");
            ResultSet rs = statement.executeQuery();
            List<EntityBookAuthor> bookAuthors = new ArrayList<>();
            while (rs.next()) {
                int idAuthor = rs.getInt("author_id");
                int idBook = rs.getInt("book_id");
                bookAuthors.add(new EntityBookAuthor(idBook, idAuthor));
            }
            return bookAuthors;
        } catch (SQLException e) {
            logger.error(e);
            return Collections.emptyList();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
    }
}
