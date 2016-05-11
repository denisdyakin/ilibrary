package ru.denisdyakin.dao;

import ru.denisdyakin.models.Book;

import java.util.List;

/**
 * Created by denisdyakin on 06/05/16.
 */
public interface BookDAO {

    public Book insert(Book book);
    public Book findByIsn(long isn);
    public List<Book> findBooksWith(int start, int offset);
    public Book updateOwner(long isn, String user_name);
    public Book update(Book oldBook, Book newBook);
    public void removeByIsn(long isn);

}
