package ru.denisdyakin.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.denisdyakin.dao.UserDAO;
import ru.denisdyakin.models.User;

import java.util.List;

@RestController
public class RestUserController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/getusers")
    public List<User> getUsers(@RequestParam(value = "start", required = false, defaultValue = "0") String start,
                               @RequestParam(value = "offset", required = false, defaultValue = "5") String offset) {
        List<User> users = userDAO.findUsersWith(Integer.parseInt(start), Integer.parseInt(offset));
        return users;
    }

    @RequestMapping(value = "/getallusers")
    public List<User> getUsers() {
        List<User> users = userDAO.findAll();
        return users;
    }

    @Transactional
    @RequestMapping(value = "/adduser")
    public User addUser(@RequestParam(value = "name", required = true) String name,
                        @RequestParam(value = "password", required = true) String password) {
        return userDAO.insert(new User(name, password));
    }

    @Transactional
    @RequestMapping(value = "/deleteuser")
    public User removeUser(@RequestParam(value = "name", required = true) String name) {
        return userDAO.remove(new User(name, null));
    }

}
