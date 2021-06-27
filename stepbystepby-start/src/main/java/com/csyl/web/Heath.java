package com.csyl.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 霖
 */
@RestController
@RequestMapping("/v1")
public class Heath {

    @GetMapping("/heath")
    public String heath() {
        return "OK";
    }
}
