package task_twelve.dao.entity.postgres;

import org.apache.log4j.Logger;
import task_twelve.entity.EntityPublisherBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PublisherBookDao {

    private static Logger logger = Logger.getLogger(PublisherBookDao.class);

    public boolean insert(EntityPublisherBook publisherBook,
                          Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO Publisher_Book VALUES (?, ?)"
            );
            statement.setInt(1, publisherBook.getIdPublisher());
            statement.setInt(2, publisherBook.getIdBook());
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

    public List<EntityPublisherBook> selectByIdPublisher(int idPublisher,
                                                         Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT publisher_id, book_id FROM Publisher_Book WHERE " +
                            "publisher_id = ?");
            statement.setInt(1, idPublisher);
            ResultSet rs = statement.executeQuery();
            List<EntityPublisherBook> publisherBook = new ArrayList<>();
            while (rs.next()) {
                int idBook = rs.getInt("book_id");
                publisherBook.add(new EntityPublisherBook(idPublisher, idBook));
            }
            return publisherBook;
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

    public List<EntityPublisherBook> selectAll(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT publisher_id, book_id FROM Publisher_Book");
            ResultSet rs = statement.executeQuery();
            List<EntityPublisherBook> publisherBook = new ArrayList<>();
            while (rs.next()) {
                int idBook = rs.getInt("book_id");
                int idPublisher = rs.getInt("publisher_id");
                publisherBook.add(new EntityPublisherBook(idPublisher, idBook));
            }
            return publisherBook;
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
