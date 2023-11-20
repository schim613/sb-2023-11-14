package com.ll.sb20231114.domain.article.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.global.rq.Rq;
import com.ll.sb20231114.global.rsData.RsData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    private final Rq rq;

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
    RsData write(
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

    @PostMapping("/article/write2")
    @SneakyThrows
    void write2(
            HttpServletRequest req,
            HttpServletResponse resp
    ) {
        String title = req.getParameter("title");
        String body = req.getParameter("body");

        Article article = articleService.write(title, body);

        RsData<Article> rs = new RsData<>(
                "S-1",
                "%d번 게시물이 작성되었습니다.".formatted(article.getId()),
                article
        );

        ObjectMapper objectMapper = new ObjectMapper();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(objectMapper.writeValueAsString(rs));
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

    @GetMapping("/article/articleServicePointer")
    @ResponseBody
    String articleServicePointer() {
        return articleService.toString(); // 객체 주소가 같음 => 스프링부트가 만들어질 때, 객체를 딱 한개 만들고 계속 사용
    }

    // 리퀘스트와 리스폰스는 요청이 있을 때 잠깐 생겼다가 사라짐
    @GetMapping("/article/httpServletRequestPointer")
    @ResponseBody
    String httpServletRequestPointer(HttpServletRequest req) {
        return req.toString();
    }

    @GetMapping("/article/httpServletResponsePointer")
    @ResponseBody
    String httpServletResponsePointer(HttpServletResponse resp) {
        return resp.toString();
    }

    @GetMapping("/article/rqPointer")
    @ResponseBody
    String rqPointer() {
        return rq.toString();
    }
}