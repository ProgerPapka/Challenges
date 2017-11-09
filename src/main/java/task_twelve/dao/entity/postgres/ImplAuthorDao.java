package task_twelve.dao.entity.postgres;

import org.apache.log4j.Logger;
import task_twelve.dao.entity.AuthorDao;
import task_twelve.entity.EntityAuthor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImplAuthorDao implements AuthorDao{

    private static Logger logger = Logger.getLogger(ImplAuthorDao.class);

    @Override
    public boolean insert(EntityAuthor author, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO Author VALUES (?, ?, ?, ?, ?)"
            );
            Date bDay = Date.valueOf(author.getbDay());
            Date dDay = null;
            if (author.getdDay() != null) {
                dDay = Date.valueOf(author.getdDay());
            }
            statement.setInt(1, author.getId());
            statement.setString(2, author.getName());
            statement.setDate(3, bDay);
            statement.setDate(4, dDay);
            statement.setString(5, author.getSex());
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

    @Override
    public EntityAuthor selectById(int id, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT id, name, birth_day, death_day, sex FROM Author WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            String name = null;
            Date bDay = null;
            Date dDay = null;
            String sex = null;
            while (rs.next()) {
                name = rs.getString("name");
                bDay = rs.getDate("birth_day");
                dDay = rs.getDate("death_day");
                sex = rs.getString("sex");
            }
            LocalDate d = null;
            LocalDate b = null;
            if (dDay != null) {
                d = dDay.toLocalDate();
            }
            if (bDay != null) {
                b = bDay.toLocalDate();
            }
            return new EntityAuthor(id, name, b, d, sex);
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

    @Override
    public List<EntityAuthor> selectByName(String name, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT id, name, birth_day, death_day, sex FROM Author WHERE name = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            List<EntityAuthor> authors = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date bDay = rs.getDate("birth_day");
                Date dDay = rs.getDate("death_day");
                String sex = rs.getString("sex");
                LocalDate d = null;
                LocalDate b = null;
                if (dDay != null) {
                    d = dDay.toLocalDate();
                }
                if (bDay != null) {
                    b = bDay.toLocalDate();
                }
                authors.add(new EntityAuthor(id, name, b, d, sex));
            }
            return authors;
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

    @Override
    public List<EntityAuthor> selectAll(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "SELECT id, name, birth_day, death_day, sex FROM Author");
            ResultSet rs = statement.executeQuery();
            List<EntityAuthor> authors = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date bDay = rs.getDate("birth_day");
                Date dDay = rs.getDate("death_day");
                String sex = rs.getString("sex");
                LocalDate d = null;
                LocalDate b = null;
                if (dDay != null) {
                    d = dDay.toLocalDate();
                }
                if (bDay != null) {
                    b = bDay.toLocalDate();
                }
                authors.add(new EntityAuthor(id, name, b, d, sex));
            }
            return authors;
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
