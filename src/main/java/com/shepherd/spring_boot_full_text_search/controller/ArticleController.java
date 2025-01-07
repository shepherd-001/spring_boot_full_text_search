package com.shepherd.spring_boot_full_text_search.controller;

import com.shepherd.spring_boot_full_text_search.data.dto.request.ArticleSearchRequest;
import com.shepherd.spring_boot_full_text_search.data.dto.response.ArticleSearchResponse;
import com.shepherd.spring_boot_full_text_search.data.model.Article;
import com.shepherd.spring_boot_full_text_search.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }


    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return articleService.getArticleById(uuid);
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        articleService.deleteArticle(uuid);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String searchText) {
        List<Article> foundArticles = articleService.searchArticles(searchText);
        if (!foundArticles.isEmpty()) {
            return ResponseEntity.ok(foundArticles);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Page<ArticleSearchResponse>> findArticles(@RequestBody ArticleSearchRequest searchRequest) {
        Page<ArticleSearchResponse> foundArticles = articleService.findArticles(searchRequest);

        if (!foundArticles.isEmpty()) {
            return ResponseEntity.ok(foundArticles);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
