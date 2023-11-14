package com.ll.sb20231114;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 액션 컨트롤러들을 한 곳에 모아 써도 되고, 나눠 써도 된다.
// 하지만 쓰임에 맞게 나누면 좋음!
@Controller
public class ArticleController {
    @GetMapping("/article/write")
    String showWrite() {
        return "article/write";
    }

    @GetMapping("/article/doWrite")
    @ResponseBody
    Article doWrite(
            String title,
            String body
    ) {
        Article article = new Article(1, title, body);

        return article;
    }
}

@AllArgsConstructor
@Getter
class Article {
    private long id;
    private String title;
    private String body;
}