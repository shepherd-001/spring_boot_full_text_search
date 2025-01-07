package com.shepherd.spring_boot_full_text_search.service;

import com.shepherd.spring_boot_full_text_search.data.dto.request.ArticleSearchRequest;
import com.shepherd.spring_boot_full_text_search.data.dto.response.ArticleSearchResponse;
import com.shepherd.spring_boot_full_text_search.data.model.Article;
import com.shepherd.spring_boot_full_text_search.data.repository.ArticleRepository;
import com.shepherd.spring_boot_full_text_search.specification.ArticleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;


    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(UUID id) {
        return articleRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Article with the provided id not found."));
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(UUID id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> searchArticles(String searchText) {
        return articleRepository.findArticlesBySearchText(searchText);
    }

    @Override
    public Page<ArticleSearchResponse> findArticles(ArticleSearchRequest searchRequest) {
        Specification<Article> specification = ArticleSpecification.searchByText(searchRequest.getSearchText());
        Pageable pageable = PageRequest.of(searchRequest.getPageNumber(), 10);
        return articleRepository.findAll(specification, pageable)
                .map(this::mapToResponse);
    }

    private ArticleSearchResponse mapToResponse(Article article) {
        return ArticleSearchResponse.builder()
                .content(article.getContent())
                .title(article.getTitle())
                .category(article.getCategory())
                .keywords(article.getKeywords())
                .publicationDate(article.getPublicationDate())
                .build();
    }
}
