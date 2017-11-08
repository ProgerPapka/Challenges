package task_twelve.dao.entity.postgres;

import org.apache.log4j.Logger;
import task_twelve.entity.EntityBook;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDao {

    private static Logger logger = Logger.getLogger(BookDao.class);

    public boolean insert(EntityBook book, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO Book VALUES (?, ?, ?)"
            );
            Date rDay = Date.valueOf(book.getrDay());
            statement.setInt(1, book.getId());
            statement.setString(2, book.getName());
            statement.setDate(3, rDay);
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

    public EntityBook selectById(int id, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT id, name, release_day FROM Book WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            String name = null;
            Date rDay = null;
            while (rs.next()) {
                name = rs.getString("name");
                rDay = rs.getDate("release_day");
            }
            LocalDate r = null;
            if (rDay != null) {
                r = rDay.toLocalDate();
            }
            return new EntityBook(id, name, r);
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

    public List<EntityBook> selectByName(String name, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT id, name, release_day FROM Book WHERE name = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            List<EntityBook> books = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date rDay = rs.getDate("release_day");
                LocalDate r = null;
                if (rDay != null) {
                    r = rDay.toLocalDate();
                }
                books.add(new EntityBook(id, name, r));
            }
            return books;
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

    public List<EntityBook> selectAll(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT id, name, release_day FROM Book");
            ResultSet rs = statement.executeQuery();
            List<EntityBook> books = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date rDay = rs.getDate("release_day");
                LocalDate r = null;
                if (rDay != null) {
                    r = rDay.toLocalDate();
                }
                books.add(new EntityBook(id, name, r));
            }
            return books;
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
