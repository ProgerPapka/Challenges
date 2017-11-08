package task_twelve.dao.entity.postgres;

import org.apache.log4j.Logger;
import task_twelve.entity.EntityPublisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PublisherDao {

    private static Logger logger = Logger.getLogger(PublisherDao.class);

    public boolean insert(EntityPublisher publisher, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO Publisher VALUES (?, ?)"
            );
            statement.setInt(1, publisher.getId());
            statement.setString(2, publisher.getName());
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

    public EntityPublisher selectById(int id, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT id, name FROM Publisher WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            String name = null;
            while (rs.next()) {
                name = rs.getString("name");
            }
            return new EntityPublisher(id, name);
        } catch (SQLException e) {
            logger.error(e);
            return null;
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

    public List<EntityPublisher> selectByName(String name, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT id, name FROM Publisher WHERE name = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            List<EntityPublisher> publishers = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                publishers.add(new EntityPublisher(id, name));
            }
            return publishers;
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

    public List<EntityPublisher> selectAll(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT id, name FROM Publisher");
            ResultSet rs = statement.executeQuery();
            List<EntityPublisher> publishers = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                publishers.add(new EntityPublisher(id, name));
            }
            return publishers;
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
