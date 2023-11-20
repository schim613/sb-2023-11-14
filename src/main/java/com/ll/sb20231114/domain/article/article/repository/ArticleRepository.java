package com.ll.sb20231114.domain.article.article.repository;

import com.ll.sb20231114.domain.article.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>(){{
        add(new Article(1L, "제목1", "내용1"));
        add(new Article(2L, "제목2", "내용2"));
        add(new Article(3L, "제목3", "내용3"));
    }};

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
}
