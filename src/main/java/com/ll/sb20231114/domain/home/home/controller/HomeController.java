package com.ll.sb20231114.domain.home.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러
public class HomeController { // HomeController가 1개 -> 싱글톤 -> 객체의 개수가 1개로 고정
    @GetMapping("/")
    public String goToArticleList() {
        return "redirect:/artice/list";
    }
}
