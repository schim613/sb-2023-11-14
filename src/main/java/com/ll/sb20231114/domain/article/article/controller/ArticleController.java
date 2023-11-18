package com.ll.sb20231114.domain.article.article.controller;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


// 액션 컨트롤러들을 한 곳에 모아 써도 되고, 나눠 써도 된다.
// 하지만 쓰임에 맞게 나누면 좋음!
@Controller
@RequiredArgsConstructor // final 필드들(입력받은 생성자)을 자동으로 생성해라
public class ArticleController {
    // @Autowired 필드 주입, final은 뺀다.
    private final ArticleService articleService;

    // @Autowired 는 생성자가 하나면 생략 가능
    // 생성자 주입 => @RequiredArgsConstructor로 자동 생성되어 생략 가능
    // public ArticleController(ArticleService articleService) {
    //     this.articleService = articleService;
    // }

    //    GET /article/write
    @GetMapping("/article/write")
    String showWrite() {
        return "article/write";
    }

    //    GET /article/doWrite?title=제목&body=내용
    @PostMapping("/article/write")
    @ResponseBody
    RsData<Article> write(
            String title,
            String body
    ) {
        Article article = articleService.write(title, body);

        RsData<Article> rs = new RsData<>(
                "S-1",
                "%d번 게시물이 작성되었습니다.".formatted(article.getId()),
                article
        );

        return rs;
    }

    //    GET /article/getLastArticle
    @GetMapping("/article/getLastArticle")
    @ResponseBody
    Article getLastArticle() {
        return articleService.findLastArticle();
    }

    //    GET /article/getArticles
    @GetMapping("/article/getArticles")
    @ResponseBody
    List<Article> getArticles() {
        return articleService.findAll();
    }
}