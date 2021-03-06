package ru.denisdyakin.dao;

import ru.denisdyakin.models.User;

import java.util.List;

public interface UserDAO {

    public User insert(User user);
    public List<User> findAll();
    public List<User> findUsersWith(int start, int offset);
    public User remove(User user);

}
