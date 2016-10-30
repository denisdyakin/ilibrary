package ru.denisdyakin.dao;

import ru.denisdyakin.models.Book;

import java.util.List;

/**
 * Created by denisdyakin on 06/05/16.
 */
public interface BookDAO {

    Book insert(Book book);
    Book findByIsn(long isn);
    List<Book> findBooksWith(int start, int offset);
    Book updateOwner(long isn, String user_name);
    Book update(Book oldBook, Book newBook);
    void removeByIsn(long isn);

}
