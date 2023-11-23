package com.ll.sb20231114.global.rq;

import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RequestScope // 잠깐 생겼다가 사라짐
@Component
@Getter
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final MemberService memberService;

    public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
        this.req = req;
        this.resp = resp;
        this.memberService = memberService;
    }

    public String redirect(String path, String msg) {
        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);

        return "redirect:" + path + "?msg=" + msg;
    }

    public long getLoginedMemberId() {
        return Optional
                .ofNullable(req.getSession().getAttribute("loginedMemberId"))
                .map(_id -> (long) _id)
                .orElse(0L);
    }

    public Member getLoginedMember() {
        long loginedMemberId = getLoginedMemberId();

        if (loginedMemberId == 0) {
            return null;
        }

        return memberService.findById(loginedMemberId).get();
    }
}
