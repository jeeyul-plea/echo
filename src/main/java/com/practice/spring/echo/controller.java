package com.practice.spring.echo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping("/echo")
    public String echoGet(@RequestParam String message) {
        return message;
    }

    @PostMapping("/echo")
    public String echoPost(@RequestParam String message) {
        return message;
    }
}
