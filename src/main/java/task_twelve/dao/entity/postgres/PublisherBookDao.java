package task_twelve.dao.entity.postgres;

import org.apache.log4j.Logger;
import task_twelve.entity.EntityPublisherBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherBookDao {

    private static Logger logger = Logger.getLogger(PublisherBookDao.class);

    private Connection connection;

    public PublisherBookDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertPublisherBook(EntityPublisherBook publisherBook) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO \"Publishers_Books\" VALUES (?, ?)"
            );
            statement.setInt(1, publisherBook.getIdPublisher());
            statement.setInt(2, publisherBook.getIdBook());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    public List<EntityPublisherBook> selectByIdPublisher(int idPublisher) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"Publishers_Books\" WHERE " +
                            "id_publisher = ?");
            statement.setInt(1, idPublisher);
            ResultSet rs = statement.executeQuery();
            List<EntityPublisherBook> publisherBook = new ArrayList<>();
            while (rs.next()) {
                int idBook = rs.getInt("id_book");
                publisherBook.add(new EntityPublisherBook(idPublisher, idBook));
            }
            return publisherBook;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public List<EntityPublisherBook> selectAll() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"Publishers_Books\"");
            ResultSet rs = statement.executeQuery();
            List<EntityPublisherBook> publisherBook = new ArrayList<>();
            while (rs.next()) {
                int idBook = rs.getInt("id_book");
                int idPublisher = rs.getInt("id_publisher");
                publisherBook.add(new EntityPublisherBook(idPublisher, idBook));
            }
            return publisherBook;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }
}
