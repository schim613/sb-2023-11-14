package com.ll.sb20231114.domain.article.article.service;

import com.ll.sb20231114.domain.article.article.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private final List<Article> articles = new ArrayList<>();

    public Article write(String title, String body) {
        Article article = new Article(articles.size() + 1, title, body);
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
