package com.ll.sb20231114.domain.article.article.repository;

import com.ll.sb20231114.domain.article.article.entity.Article;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component // Bean임 => 여러 스코프 중 하나?
public class ArticleArrayList extends ArrayList<Article> {
}
