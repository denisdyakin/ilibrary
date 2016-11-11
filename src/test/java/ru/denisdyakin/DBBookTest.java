package ru.denisdyakin;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.denisdyakin.dao.BookDAO;
import ru.denisdyakin.dao.rowmapper.BookRowMapper;
import ru.denisdyakin.models.Book;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class DBBookTest {

    @Autowired
    BookDAO bookDAO;

    @Autowired
    JdbcTemplate template;

    @Test
    public void testBookSaver() {
        long isn = 33L;
        String author = "Normal author";
        String name = "Normal name";
        Book rightBook = new Book(isn, name, author, null);

        bookDAO.insert(rightBook);

        List<Book> books = template.query("select * from book where isn=33", new BookRowMapper());

        Assert.assertEquals(1, books.size());
        Assert.assertEquals(isn, books.get(0).getIsn());
        Assert.assertEquals(name, books.get(0).getName());
        Assert.assertEquals(author, books.get(0).getAuthor());
    }

    @Test
    public void testBookFinder() {
        long isn = 1L;
        String author = "Бредберри";
        String name = "451 градус по Фаренгейту";

        Book book = bookDAO.findByIsn(isn);

        Assert.assertEquals(name, book.getName());
        Assert.assertEquals(author, book.getAuthor());
    }

    @Test
    public void testRemoveBook() {

    }

}
