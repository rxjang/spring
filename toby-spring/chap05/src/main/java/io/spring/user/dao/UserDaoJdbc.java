package io.spring.user.dao;

import io.spring.user.domain.Level;
import io.spring.user.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setLevel(Level.valueOf(rs.getInt("level")));
            user.setLogin(rs.getInt("login"));
            user.setRecommend(rs.getInt("recommend"));
            return user;
        }
    };

    public void add(final User user) {
        this.jdbcTemplate.update("INSERT INTO  users(id, name, password, level, login, recommend) VALUES (?, ?, ?, ?, ?, ?)",
                user.getId(), user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());
    }

    public User get(String id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?",
                new Object[]{id}, this.userMapper);
    }

    public void deleteAll() {
      this.jdbcTemplate.update("DELETE FROM users");
    }

    public int getCount() {
        return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update(
                "UPDATE USERS SET name = ?, password = ?, level = ?, login = ?, recommend = ? WHERE id = ?",
                user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId());
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM users ORDER BY id", this.userMapper);
    }
}
