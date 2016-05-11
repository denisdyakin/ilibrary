package ru.denisdyakin.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.denisdyakin.dao.BookDAO;
import ru.denisdyakin.models.Book;

import java.util.List;

/**
 * Created by denisdyakin on 08/05/16.
 */

@RestController
public class RestBookController {

    @Autowired
    BookDAO bookDAO;

    @RequestMapping(value = "/getbooks")
    public List<Book> getBooksWithLimits(@RequestParam(value = "start", required = false, defaultValue = "0") String start,
                                         @RequestParam(value = "offset", required = false, defaultValue = "5") String offset)
    {
        List<Book> books = bookDAO.findBooksWith(Integer.parseInt(start), Integer.parseInt(offset));
        return books;
    }

    @Transactional
    @RequestMapping(value = "/deletebook")
    public void deleteBookWithIsn(@RequestParam(value = "isn", required = true) String isn) {
        bookDAO.removeByIsn(Long.parseLong(isn));
    }

    @Transactional
    @RequestMapping(value = "/addbook")
    public Book addBook(@RequestParam(value = "isn", required = true) String isn,
                           @RequestParam(value = "author", required = true) String author,
                           @RequestParam(value = "name", required = true) String name)
    {
        return bookDAO.insert(new Book(Long.parseLong(isn), author, name, null));
    }

    @Transactional
    @RequestMapping(value = "/updateownerofbook")
    public Book takeBook(@RequestParam(value = "isn", required = true) String isn,
                        @RequestParam(value = "user_name", required = false) String user_name)
    {
        Book oldBook = bookDAO.findByIsn(Long.parseLong(isn));
        Book newBook = new Book(oldBook.getIsn(), oldBook.getName(), oldBook.getAuthor(), user_name);
        return bookDAO.update(oldBook, newBook);
    }
}
