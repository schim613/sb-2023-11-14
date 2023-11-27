package com.ll.sb20231114.domain.member.member.controller;

import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.service.MemberService;
import com.ll.sb20231114.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/member/login")
    String showLogin() {
        return "member/member/login";
    }

    @Data
    public static class LoginForm {
        @NotBlank(message = "아이디를 입력해주세요.")
        private String username;
        @NotBlank @NotNull
        private String password;
    }

    @PostMapping("/member/login")
    String login(@Valid LoginForm loginForm) {
        Member member = memberService.findByUsername(loginForm.username).get();

        if (!member.getPassword().equals(loginForm.password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 처리
        // 요청과 관련된 세션 저장소
        // 현재 접속한 브라우저(rq)한테 정보를 저장한다. 너는 이 회원이야.
        rq.setSessionAttr("loginedMemberId", member.getId());

        return rq.redirect("/article/list", "로그인이 완료되었습니다.");
    }

    @GetMapping("/member/logout")
    String logout() {
        rq.removeSessionAttr("loginedMemberId");

        return rq.redirect("/article/list", "로그아웃 되었습니다.");
    }

    @GetMapping("/member/join")
    String showJoin() {
        return "member/member/join";
    }

    @Data
    public static class JoinForm {
        @NotBlank(message = "아이디를 입력해주세요.")
        private String username;
        @NotBlank @NotNull
        private String password;
    }

    @PostMapping("/member/join")
    String join(@Valid JoinForm joinForm) {
        memberService.join(joinForm.username, joinForm.password);

        return rq.redirect("/member/login", "회원가입이 완료되었습니다.");
    }
}