package com.ll.sb20231114.domain.article.article.service;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 단 한번만 생성되고, 그 이후에는 재사용되는 객체임
public class ArticleService {
    // @Autowired 필드 주입
    private final ArticleRepository articleRepository;

    // @Autowired 생략 가능
    public ArticleService(ArticleRepository articleRepository) { // 생성자 주입
        this.articleRepository = articleRepository;
    }

    public Article write(String title, String body) {
        Article article = new Article(title, body);

        articleRepository.save(article);

        return article;
    }

    public Article findLastArticle() {
        return articleRepository.findLastArticle();
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
