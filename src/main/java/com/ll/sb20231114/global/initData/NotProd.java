package com.ll.sb20231114.global.initData;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {
    @Bean // 스프링부트가 시작될 때 실행이 돼서 initNotProd 이름의 빈이 등록됨
    public ApplicationRunner initNotProd( // 우리가 명시적으로 호출하지 않아도 프로그램 시작할 때 호출됨
//            MemberRepository memberRepository,
//            ArticleRepository articleRepository
    ) {
        return args -> {
            System.out.println("안녕");
        };
    }
}
