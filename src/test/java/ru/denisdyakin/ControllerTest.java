package ru.denisdyakin;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.denisdyakin.controller.rest.RestBookController;
import ru.denisdyakin.dao.rowmapper.BookRowMapper;
import ru.denisdyakin.models.Book;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class ControllerTest {

    @Autowired
    RestBookController controller;

    @Autowired
    JdbcTemplate template;

    @Test
    public void testAddBookWithLimits() {
        Long isn = 22L;
        String author = "Normal author";
        String name = "Normal name";

        Long badIsn = 1L;
        String badAuthor = "Bad author name ndmsndamnd,man,smnd,mand,mnad,mnam,dmandm,and,m";
        String badName = "Bad name hfjkshdfkjshfkjhslkjhfkjdfhklhdjhfjkdhkfjhgdkjfghkdjfgkjnkdnfbkjdnkbjndkjbndkfbndkjbnfdjkb";

        Book wrongBookIsn = new Book(badIsn, "Normal author", "Normal name", null);
        Book wrongBookAthor = new Book(44L, badAuthor, "Normal name", null);
        Book wrongBookName = new Book(55L, "Normal author", badName, null);

        controller.addBook(isn.toString(), author, name);
        try{
            controller.addBook(wrongBookIsn.getIsn() + "", wrongBookIsn.getAuthor(), wrongBookIsn.getName());
        } catch (Exception ex) {
            System.out.println("wrong isn");
        }
        try{
            controller.addBook(wrongBookAthor.getIsn() + "", wrongBookAthor.getAuthor(), wrongBookAthor.getName());
        } catch (Exception ex) {
            System.out.println("wrong author");
        }
        try{
            controller.addBook(wrongBookName.getIsn() + "", wrongBookName.getAuthor(), wrongBookName.getName());
        } catch (Exception ex) {
            System.out.println("wrong name");
        }

        controller.addBook("32", "", "Book's name");

        List<Book> list = template.query("select * from book", new BookRowMapper());

        Assert.assertEquals(6, list.size());
    }

}
