package com.ll.sb20231114.domain.article.article.controller;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.service.MemberService;
import com.ll.sb20231114.global.rq.Rq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


// 액션 컨트롤러들을 한 곳에 모아 써도 되고, 나눠 써도 된다.
// 하지만 쓰임에 맞게 나누면 좋음!
@Controller
@RequiredArgsConstructor // final 필드들(입력받은 생성자)을 자동으로 생성해라
public class ArticleController {
    // @Autowired 필드 주입, final은 뺀다.
    private final ArticleService articleService;
    private final MemberService memberService;
    private final Rq rq;

    //    GET /article/doWrite?title=제목&body=내용
    @GetMapping("/article/list")
    String showList(Model model, HttpServletRequest req) {
        long loginedMemberId = Optional
                .ofNullable(req.getSession().getAttribute("loginedMemberId"))
                .map(id -> (long) id)
                .orElse(0L);

        if(loginedMemberId > 0) {
            Member loginedMember = memberService.findById(loginedMemberId).get();
            model.addAttribute("LoginedMemberId", loginedMember);
        }

        List<Article> articles = articleService.findAll();

        model.addAttribute("articles", articles);

        return "article/article/list";
    }

    @GetMapping("/article/detail/{id}")
    String showDetail(Model model, @PathVariable long id, HttpServletRequest req) {
        long loginedMemberId = Optional
                .ofNullable(req.getSession().getAttribute("loginedMemberId"))
                .map(_id -> (long) _id)
                .orElse(0L);

        if(loginedMemberId > 0) {
            Member loginedMember = memberService.findById(loginedMemberId).get();
            model.addAttribute("LoginedMemberId", loginedMember);
        }

        Article article = articleService.findById(id).get();


        model.addAttribute("article", article);

        return "article/article/detail";
    }

    //    GET /article/write
    @GetMapping("/article/write")
    String showWrite() {
        HttpServletRequest req = rq.getReq();

        long loginedMemberId = rq.getLoginedMemberId();

        if(loginedMemberId > 0) {
            Member loginedMember = rq.getLoginedMember();
            req.setAttribute("loginedMemberId", loginedMember);
        }

        return "article/article/write";
    }

    @Data
    public static class WriteForm {
        @NotBlank(message = "제목 좀..")
        private String title;
        @NotBlank
        private String body;
    }

    //    GET /article/doWrite?title=제목&body=내용
    @PostMapping("/article/write")
    String write(@Valid WriteForm writeForm) {
        Article article = articleService.write(writeForm.title, writeForm.body);

        return rq.redirect("/article/list", "%d번 게시물이 생성되었습니다.".formatted(article.getId()));
    }

    @GetMapping("/article/modify/{id}")
    String showModify(Model model, @PathVariable long id) {
        Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "/article/article/modify";
    }

    @Data
    public static class ModifyForm {
        @NotBlank(message = "제목 좀..")
        private String title;
        @NotBlank
        private String body;
    }

    @PostMapping("/article/modify/{id}")
    String modify(@PathVariable long id, @Valid ModifyForm modifyForm) {
        articleService.modify(id, modifyForm.title, modifyForm.body);

        return rq.redirect("/article/list", "%d번 게시물이 수정되었습니다.".formatted(id));
    }

    @GetMapping("/article/delete/{id}")
    String delete(@PathVariable long id) {
        articleService.delete(id);

        return rq.redirect("/article/list", "%d번 게시물이 삭제되었습니다.".formatted(id));
    }
}