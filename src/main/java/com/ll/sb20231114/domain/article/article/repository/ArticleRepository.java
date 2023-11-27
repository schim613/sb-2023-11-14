package com.ll.sb20231114.domain.article.article.repository;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.repository.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepository {
    private final MemberRepository memberRepository;

    private final List<Article> articles = new ArrayList<>();

    public ArticleRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

        Member member1 = memberRepository.findById(1L).get();
        Member member2 = memberRepository.findById(2L).get();

        save(new Article(member1, "title1", "content1"));
        save(new Article(member2, "title2", "content2"));
        save(new Article(member2, "title3", "content3"));
    }

    public Article save(Article article) {
        if (article.getId() == 0) {
            article.setId(articles.size() + 1L);
        }

        articles.add(article);

        return article;
    }

    public Article findLastArticle() {
        return articles.getLast();
    }

    public List<Article> findAll() {
        return articles;
    }

    public Optional<Article> findById(long id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst();
    }

    public void delete(long id) {
        articles.removeIf(article -> article.getId() == id);
    }
}
