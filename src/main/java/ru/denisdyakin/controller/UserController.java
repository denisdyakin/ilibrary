package ru.denisdyakin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by denisdyakin on 08/05/16.
 */
@Controller
public class UserController {

    @RequestMapping(name = "/user", method = RequestMethod.GET)
    public String users(ModelMap model)
    {
        return "user/users";
    }

}
