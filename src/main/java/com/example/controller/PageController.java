package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on : April 24, 2018
 * Author     : Kurniawan Hendi Wijaya
 * Name       : Hendi
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String index (Model model)
    {
        model.addAttribute("title","Home");
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
