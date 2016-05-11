package ru.denisdyakin.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.denisdyakin.dao.UserDAO;
import ru.denisdyakin.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by denisdyakin on 06/05/16.
 */
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

    public User insert(User user) {
        String insertStr = "INSERT INTO user " +
                "(name, password) VALUES(?, ?)";
        getJdbcTemplate().update(insertStr, new Object[] { user.getName(), user.getPassword() });
        return user;
    }

    public List<User> findUsersWith(int start, int offset) {
        String selectStr = "SELECT * FROM user LIMIT ?, ?";
        List<User> users = new ArrayList<User>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(selectStr, new Object [] { start, offset });
        for (Map row:
                rows) {
            if (row.get("name") != null)
            {
                User user = new User(String.valueOf(row.get("name")), String.valueOf(row.get("password")));
                users.add(user);
            }
        }
        return users;
    }

    public List<User> findAll() {
        String selectStr = "SELECT * FROM user ORDER BY name";
        List<User> users = new ArrayList<User>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(selectStr);
        for (Map row:
                rows) {
            if (row.get("name") != null)
            {
                User user = new User(String.valueOf(row.get("name")), String.valueOf(row.get("password")));
                users.add(user);
            }
        }
        return users;
    }

    public User remove(User user) {
        String removeStr = "DELETE FROM user WHERE name = ?";
        int row  = getJdbcTemplate().update(removeStr, new Object[] { user.getName() });
        return user;
    }

}
