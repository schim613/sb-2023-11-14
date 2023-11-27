package com.ll.sb20231114.domain.article.article.service;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.repository.ArticleRepository;
import com.ll.sb20231114.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 단 한번만 생성되고, 그 이후에는 재사용되는 객체임
@RequiredArgsConstructor
public class ArticleService {
    // @Autowired 필드 주입
    private final ArticleRepository articleRepository;

    // 생성자 주입
    // @Autowired는 생성자가 하나면 생략 가능
    // public MemberService(MemberRepository articleRepository) {
    //     this.articleRepository = articleRepository;
    // }

    public Article write(Member author, String title, String body) {
        Article article = new Article(author, title, body);

        articleRepository.save(article);

        return article;
    }

    public Article findLastArticle() {
        return articleRepository.findLastArticle();
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(long id) {
        return articleRepository.findById(id);
    }

    public void delete(long id) {
        articleRepository.delete(id);
    }

    public void modify(long id, String title, String body) {
        Article article = findById(id).get();
        article.setTitle(title);
        article.setBody(body);
    }
}
