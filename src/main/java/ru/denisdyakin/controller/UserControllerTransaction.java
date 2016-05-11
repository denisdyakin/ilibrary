package ru.denisdyakin.controller;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.denisdyakin.dao.impl.UserDAOImpl;
import ru.denisdyakin.models.User;

/**
 * Created by denisdyakin on 10/05/16.
 */
public class UserControllerTransaction extends UserDAOImpl{
    private PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager txManager) {
        this.transactionManager = txManager;
    }

    public User insert(User user)
    {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            super.insert(user);
            transactionManager.commit(txStatus);
            return user;
        } catch (RuntimeException e) {
            transactionManager.rollback(txStatus);
            return null;
        }
    }

    public User remove(User user)
    {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            super.remove(user);
            transactionManager.commit(txStatus);
            return user;
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            return null;
        }
    }

}
