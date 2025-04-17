package com.example.firstproject.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//REST API 전용 - 컨트롤러 선언
@RestController
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello(){
        return "hello world";
    }


}