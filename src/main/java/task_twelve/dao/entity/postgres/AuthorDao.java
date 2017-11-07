package task_twelve.dao.entity.postgres;

import org.apache.log4j.Logger;
import task_twelve.entity.EntityAuthor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

    private static Logger logger = Logger.getLogger(AuthorDao.class);

    private Connection connection;

    public AuthorDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertAuthor(EntityAuthor author) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO \"Author\" VALUES (?, ?, ?, ?, ?)"
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
        }
    }

    public EntityAuthor selectById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"Author\" WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            String name = null;
            Date bDay = null;
            Date dDay = null;
            String sex = null;
            while (rs.next()) {
                name = rs.getString("name");
                bDay = rs.getDate("b_day");
                dDay = rs.getDate("d_day");
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
        }
    }

    public List<EntityAuthor> selectByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"Author\" WHERE \"Author\".name = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            List<EntityAuthor> authors = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date bDay = rs.getDate("b_day");
                Date dDay = rs.getDate("d_day");
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
            return null;
        }
    }

    public List<EntityAuthor> selectAll() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"Author\"");
            ResultSet rs = statement.executeQuery();
            List<EntityAuthor> authors = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date bDay = rs.getDate("b_day");
                Date dDay = rs.getDate("d_day");
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
            return null;
        }
    }
}
