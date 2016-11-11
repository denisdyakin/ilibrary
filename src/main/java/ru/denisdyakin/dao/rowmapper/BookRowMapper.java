package ru.denisdyakin.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.denisdyakin.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("rawtypes")
public class BookRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book(rs.getLong("isn"), rs.getString("name"), rs.getString("author"), rs.getString("user_name"));
        return book;
    }

}
