package task_twelve.dao.entity;

import task_twelve.entity.EntityPublisherBook;

import java.sql.Connection;
import java.util.List;

public interface PublisherBookDao {

    boolean insert(EntityPublisherBook publisherBook, Connection connection);

    List<EntityPublisherBook> selectByIdPublisher(int idPublisher, Connection connection);

    List<EntityPublisherBook> selectAll(Connection connection);
}
