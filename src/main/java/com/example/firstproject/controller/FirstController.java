package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;




@Controller
public class FirstController {
    //hello
    @GetMapping("/hello") //url 요청 접수
    public String niceToMeetYou(Model model){ //모델 객체 받아오기
        //모델 객체(크리스탈) 값을 - 변수에 연결
        model.addAttribute("username","name");
        return "greetings"; //greetings.mustache 파일 반환
    }

    //bye
    @GetMapping("/bye")
    public String seeYouNext(Model model){ //모델 객체 받아오기
        //모델 객체(크리스탈) 값을 - 변수에 연결
        model.addAttribute("nickname","홍길동");
        return "goodbye"; //greetings.mustache 파일 반환
    }


}
