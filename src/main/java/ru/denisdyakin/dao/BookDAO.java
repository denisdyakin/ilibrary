package ru.denisdyakin.dao;

import ru.denisdyakin.models.Book;

import java.util.List;

public interface BookDAO {

    Book insert(Book book);
    Book findByIsn(long isn);
    List<Book> findBooksWith(int start, int offset);
    Book updateOwner(long isn, String user_name);
    Book update(Book oldBook, Book newBook);
    void removeByIsn(long isn);

}
