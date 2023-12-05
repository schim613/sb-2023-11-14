package com.ll.sb20231114.domain.home.home.controller;

import com.ll.sb20231114.global.rq.Rq;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Controller // 컨트롤러
@RequiredArgsConstructor
public class HomeController { // HomeController가 1개 -> 싱글톤 -> 객체의 개수가 1개로 고정
    private final Rq rq;

    @GetMapping("/")
    public String goToArticleList(String msg) {
        msg = (msg != null) ? msg : "";

        return rq.redirect("/article/list", msg);
    }

    // 세션의 내용을 보여준다.
    @GetMapping("/home/session")
    @ResponseBody
    public Map<String, Object> showSession(HttpSession session) {
        return Collections.list(session.getAttributeNames()).stream()
                .collect(
                        Collectors.toMap(
                                key -> key,
                                key -> session.getAttribute(key)
                        )
                );
    }
}
