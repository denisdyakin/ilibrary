package ru.denisdyakin.controller.rest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.denisdyakin.controller.BookControllerTransaction;
import ru.denisdyakin.dao.BookDAO;
import ru.denisdyakin.models.Book;

import java.util.List;

/**
 * Created by denisdyakin on 08/05/16.
 */

@RestController
public class RestBookController {

    @RequestMapping(value = "/getbooks")
    public List<Book> getBooksWithLimits(@RequestParam(value = "start", required = false, defaultValue = "0") String start,
                                         @RequestParam(value = "offset", required = false, defaultValue = "5") String offset)
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDAO bookDAO = (BookDAO) context.getBean("bookDAO");
        List<Book> books = bookDAO.findBooksWith(Integer.parseInt(start), Integer.parseInt(offset));
        return books;
    }

    @RequestMapping(value = "/deletebook")
    public void deleteBookWithIsn(@RequestParam(value = "isn", required = true) String isn) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookControllerTransaction bookControllerTransaction = (BookControllerTransaction) context.getBean("bookControllerTransaction") ;
        try {
            bookControllerTransaction.removeByIsnTx(Long.parseLong(isn));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addbook")
    public Book addBook(@RequestParam(value = "isn", required = true) String isn,
                           @RequestParam(value = "author", required = true) String author,
                           @RequestParam(value = "name", required = true) String name)
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookControllerTransaction bookControllerTransaction = (BookControllerTransaction) context.getBean("bookControllerTransaction") ;
        return bookControllerTransaction.insert(new Book(Long.parseLong(isn), author, name, null));
    }

    @RequestMapping(value = "/updateownerofbook")
    public Book takeBook(@RequestParam(value = "isn", required = true) String isn,
                        @RequestParam(value = "user_name", required = false) String user_name)
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookControllerTransaction bookControllerTransaction = (BookControllerTransaction) context.getBean("bookControllerTransaction") ;
        Book oldBook = bookControllerTransaction.findByIsn(Long.parseLong(isn));
        Book newBook = new Book(oldBook.getIsn(), oldBook.getName(), oldBook.getAuthor(), user_name);
        return bookControllerTransaction.update(oldBook, newBook);
    }
}
