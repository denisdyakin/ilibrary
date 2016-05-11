package ru.denisdyakin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by denisdyakin on 08/05/16.
 */
@Controller
public class BookController {

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String books(ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
//        Collection<SimpleGrantedAuthority> list = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
//        List<String> roles = new ArrayList<String>();
//        for (SimpleGrantedAuthority simpleGrantedAuthority : list)
//        {
//            roles.add(simpleGrantedAuthority.getAuthority());
//        }
//
//        model.addAttribute("roles", roles);
        model.addAttribute("name", name);
        return "book/books";
    }

}
