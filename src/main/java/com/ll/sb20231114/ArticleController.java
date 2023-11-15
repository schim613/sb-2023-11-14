package com.ll.sb20231114;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 액션 컨트롤러들을 한 곳에 모아 써도 되고, 나눠 써도 된다.
// 하지만 쓰임에 맞게 나누면 좋음!
@Controller
public class ArticleController {
    private List<Article> articles = new ArrayList<>(); // 전역 변수 = 인스턴스 변수

    @GetMapping("/article/write")
    String showWrite() {
        return "article/write";
    }

    @GetMapping("/article/doWrite")
    @ResponseBody
    Map<String, Object> doWrite(
            String title,
            String body
    ) {
        Article article = new Article(articles.size() + 1, title, body);

        Map<String, Object> rs = new HashMap<>();
        rs.put("msg", "%d번 게시물이 작성되었습니다.".formatted(article.getId()));
        rs.put("data", article);

        articles.add(article);

        return rs;
    }

    @GetMapping("/article/getLastArticle")
    @ResponseBody
    Article getLastArticle () {
        return articles.getLast();
    }

    @GetMapping("/article/getArticles")
    @ResponseBody
    List<Article> getArticles () {
        return articles;
    }
}

@AllArgsConstructor
@Getter
class Article {
    private long id;
    private String title;
    private String body;
}