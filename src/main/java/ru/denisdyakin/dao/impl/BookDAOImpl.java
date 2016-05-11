package ru.denisdyakin.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.denisdyakin.dao.BookDAO;
import ru.denisdyakin.dao.rowmapper.BookRowMapper;
import ru.denisdyakin.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by denisdyakin on 06/05/16.
 */
public class BookDAOImpl extends JdbcDaoSupport implements BookDAO {

    public Book insert(Book book) {
        String insertStr = "INSERT INTO book " +
                "(isn, author, name, user_name) VALUES(?, ?, ?, ?)";
        getJdbcTemplate().update(insertStr, new Object[] { book.getIsn(), book.getAuthor(), book.getName(), book.getUser_name() });
        return book;
    }

    @SuppressWarnings({ "unchecked" })
    public Book findByIsn(long isn) {
        String selectStr = "SELECT * FROM book WHERE isn = ?";
        Book book = (Book) getJdbcTemplate().queryForObject(selectStr, new Object[] { isn }, new BookRowMapper());
        return book;
    }

    public List<Book> findBooksWith(int start, int offset) {
        String selectStr = "SELECT * FROM book ORDER BY author LIMIT ?, ? ";
        List<Book> books = new ArrayList<Book>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(selectStr, new Object [] { start, offset });
        for (Map row:
                rows) {
            if (row.get("isn") != null)
            {
                Book book = new Book(Long.valueOf(String.valueOf(row.get("isn"))), String.valueOf(row.get("author")), String.valueOf(row.get("name")), String.valueOf(row.get("user_name")));
                books.add(book);
            }
        }

        return books;
    }

    public Book update(Book oldBook, Book newBook)
    {
        return newBook;
    }

    public Book updateOwner(long isn, String user_name) {
        String updateString = "UPDATE book SET user_name=? WHERE isn=?";
        getJdbcTemplate().update(updateString, new Object[] { user_name, isn });
        return findByIsn(isn);
    }

    public void removeByIsn(long isn) {
        String removeStr = "DELETE FROM book WHERE isn = ?";
        int row  = getJdbcTemplate().update(removeStr, new Object[] { isn });
    }
}
