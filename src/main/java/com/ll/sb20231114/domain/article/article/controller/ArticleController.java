package com.ll.sb20231114.domain.article.article.controller;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


// 액션 컨트롤러들을 한 곳에 모아 써도 되고, 나눠 써도 된다.
// 하지만 쓰임에 맞게 나누면 좋음!
@Controller
@RequiredArgsConstructor // final 필드들(입력받은 생성자)을 자동으로 생성해라
public class ArticleController {
    // @Autowired 필드 주입, final은 뺀다.
    private final ArticleService articleService;

    //    GET /article/doWrite?title=제목&body=내용
    @GetMapping("/article/list")
    String showList(Model model) {
        List<Article> articles = articleService.findAll();

        model.addAttribute("articles", articles);

        return "article/list";
    }

    @GetMapping("/article/detail/{id}")
    String showDetail(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "article/detail";
    }

    //    GET /article/write
    @GetMapping("/article/write")
    String showWrite() {
        return "article/write";
    }

    @Data
    public static class WriteForm {
        @NotBlank(message = "제목 좀..")
        private String title;
        @NotBlank @NotNull
        private String body;
    }

    //    GET /article/doWrite?title=제목&body=내용
    @PostMapping("/article/write")
    String write(@Valid WriteForm writeForm) {
        Article article = articleService.write(writeForm.title, writeForm.body);

        String msg = "%d번 게시물 생성되었습니다.".formatted(article.getId());

        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);

        return "redirect:/article/list?msg=" + msg;
    }

    @GetMapping("/article/modify/{id}")
    String showModify(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "/article/modify";
    }

    @Data
    public static class ModifyForm {
        @NotBlank(message = "제목 좀..")
        private String title;
        @NotBlank
        private String body;
    }

    @PostMapping("/article/modify/{id}")
    String write(@PathVariable long id, @Valid ModifyForm modifyForm) {
        articleService.modify(id, modifyForm.title, modifyForm.body);

        String msg = "id %d, article is modified".formatted(id);

        return "redirect:/article/list?msg=" + msg;
    }

    @GetMapping("/article/delete/{id}")
    String delete(@PathVariable long id) {
        articleService.delete(id);

        String msg = "id %d, article is deleted".formatted(id);

        return "redirect:/article/list?msg=" + msg;
    }
}