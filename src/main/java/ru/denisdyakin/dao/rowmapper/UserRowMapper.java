package ru.denisdyakin.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.denisdyakin.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("rawtypes")
public class UserRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString("name"), rs.getString("password"));
        return user;
    }

}
