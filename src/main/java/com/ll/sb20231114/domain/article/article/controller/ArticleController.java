package com.ll.sb20231114.domain.article.article.controller;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// 액션 컨트롤러들을 한 곳에 모아 써도 되고, 나눠 써도 된다.
// 하지만 쓰임에 맞게 나누면 좋음!
@Controller
@RequiredArgsConstructor // final 필드들(입력받은 생성자)을 자동으로 생성해라
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    private final Rq rq;

    @GetMapping("/list")
    String showList(Model model) {
        List<Article> articles = articleService.findAll();

        model.addAttribute("articles", articles);

        return "article/article/list";
    }

    @GetMapping("/detail/{id}")
    String showDetail(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "article/article/detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    String showWrite() {
        return "article/article/write";
    }

    @Data
    public static class WriteForm {
        @NotBlank(message = "제목 좀..")
        private String title;
        @NotBlank
        private String body;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    String write(@Valid WriteForm writeForm) {
        Article article = articleService.write(rq.getMember(), writeForm.title, writeForm.body);

        return rq.redirect("/article/list", "%d번 게시물이 생성되었습니다.".formatted(article.getId()));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    String showModify(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

        if (!articleService.canModify(rq.getMember(), article)) throw new RuntimeException("수정 권한이 없습니다.");

        model.addAttribute("article", article);

        return "/article/article/modify";
    }

    @Data
    public static class ModifyForm {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/modify/{id}")
    String modify(@PathVariable long id, @Valid ModifyForm modifyForm) {
        Article article = articleService.findById(id).get();

        if (!articleService.canModify(rq.getMember(), article)) throw new RuntimeException("수정 권한이 없습니다.");

        articleService.modify(article, modifyForm.title, modifyForm.body);

        return rq.redirect("/article/list", "%d번 게시물이 수정되었습니다.".formatted(id));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable long id) {
        Article article = articleService.findById(id).get();

        if (!articleService.canDelete(rq.getMember(), article)) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        articleService.delete(article);

        return rq.redirect("/article/list", "%d번 게시물이 삭제되었습니다.".formatted(id));
    }

    @Data
    public static class ArticleCreateForm {
        @NotBlank(message = "제목을 입력해주세요.")
        private String title;
        @NotBlank(message = "내용을 입력해주세요.")
        private String body;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write2")
    String showWrite2(ArticleCreateForm articleCreateForm) {
        return "article/article/write2";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write2")
    String write2(@Valid ArticleCreateForm articleCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article/article/write2";
        }

        return "redirect:/";
    }

}