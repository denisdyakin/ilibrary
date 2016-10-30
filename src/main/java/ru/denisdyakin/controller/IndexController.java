package ru.denisdyakin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by denisdyakin on 10/05/16.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndex()
    {
        return "/index";
    }

}
