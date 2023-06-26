package com.applyskillproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @GetMapping("/money")
    public String money() {
        return "Send Money";
    }

    @PostMapping("/money")
    public String postMoney() {
        return "Post Money";
    }
}
