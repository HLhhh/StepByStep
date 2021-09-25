package com.csyl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 霖
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String healthy() {
        return "/calendar";
    }
}
