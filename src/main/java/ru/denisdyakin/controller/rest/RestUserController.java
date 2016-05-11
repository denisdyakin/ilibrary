package ru.denisdyakin.controller.rest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.denisdyakin.controller.UserControllerTransaction;
import ru.denisdyakin.dao.UserDAO;
import ru.denisdyakin.models.User;

import java.util.List;

/**
 * Created by denisdyakin on 09/05/16.
 */
@RestController
public class RestUserController {

    @RequestMapping(value = "/getusers")
    public List<User> getUsers(@RequestParam(value = "start", required = false, defaultValue = "0") String start,
                               @RequestParam(value = "offset", required = false, defaultValue = "5") String offset)
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        List<User> users = userDAO.findUsersWith(Integer.parseInt(start), Integer.parseInt(offset));
        return users;
    }

    @RequestMapping(value = "/getallusers")
    public List<User> getUsers()
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        List<User> users = userDAO.findAll();
        return users;
    }

    @RequestMapping(value = "/adduser")
    public User addUser(@RequestParam(value = "name", required = true) String name,
                        @RequestParam(value = "password", required = true) String password)
    {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserControllerTransaction controllerTransaction = (UserControllerTransaction) context.getBean("userControllerTransaction");
        return controllerTransaction.insert(new User(name, password));
    }

    @RequestMapping(value = "/deleteuser")
    public User removeUser(@RequestParam(value = "name", required = true) String name)
    {
        ConfigurableApplicationContext configurableApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserControllerTransaction controllerTransaction = (UserControllerTransaction) configurableApplicationContext.getBean("userControllerTransaction");
        return controllerTransaction.remove(new User(name, null));
    }


}
