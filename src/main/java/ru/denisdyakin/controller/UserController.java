package ru.denisdyakin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @RequestMapping(name = "/user")
    public String users() {
        return "user/users";
    }

}
