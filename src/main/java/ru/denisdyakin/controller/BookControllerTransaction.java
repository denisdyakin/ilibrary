package ru.denisdyakin.controller;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.denisdyakin.dao.impl.BookDAOImpl;
import ru.denisdyakin.models.Book;

/**
 * Created by denisdyakin on 09/05/16.
 */
public class BookControllerTransaction extends BookDAOImpl{
    private PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager txManager) {
        this.transactionManager = txManager;
    }

    public Book insert(Book book)
    {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            super.insert(book);
            transactionManager.commit(txStatus);
            return book;
        } catch (RuntimeException e) {
            transactionManager.rollback(txStatus);
            return null;
        }
    }

    public void removeByIsnTx(long isn) throws Exception {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            super.removeByIsn(isn);
            transactionManager.commit(txStatus);
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            e.printStackTrace();
        }
    }

    public Book updateOwner(long isn, String user_name)
    {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            Book book = super.updateOwner(isn, user_name);
            transactionManager.commit(txStatus);
            return book;
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            e.printStackTrace();
            return null;
        }
    }

    public Book update(Book oldBook, Book newBook)
    {
        this.removeByIsn(oldBook.getIsn());
        this.insert(newBook);
        return newBook;
    }
}
