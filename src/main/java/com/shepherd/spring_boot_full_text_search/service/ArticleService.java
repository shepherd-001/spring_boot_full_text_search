package com.shepherd.spring_boot_full_text_search.service;

import com.shepherd.spring_boot_full_text_search.data.model.Article;

import java.util.List;
import java.util.UUID;

public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(UUID id);
    Article createArticle(Article article);
    void deleteArticle(UUID id);
    List<Article> searchArticles(String searchText);
    List<Article> findArticles(String searchText);
}
