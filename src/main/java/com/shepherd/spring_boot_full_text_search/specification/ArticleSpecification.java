package com.shepherd.spring_boot_full_text_search.specification;

import com.shepherd.spring_boot_full_text_search.data.model.Article;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ArticleSpecification {
    public static Specification<Article> searchByText(String searchText) {
        return(root, query, criteriaBuilder) -> {
            if(searchText == null || searchText.isBlank())
                return criteriaBuilder.conjunction();
            String pattern = "%" + searchText.toLowerCase() + "%";
            Predicate contentPredicate = criteriaBuilder.like(root.get("content"), pattern);
            Predicate titlePredicate = criteriaBuilder.like(root.get("title"), pattern);
            Predicate authorPredicate = criteriaBuilder.like(root.get("author"), pattern);
            Predicate categoryPredicate = criteriaBuilder.like(root.get("category"), pattern);
            Predicate keywordPredicate = criteriaBuilder.like(root.get("keywords"), pattern);

            return criteriaBuilder.or(contentPredicate, titlePredicate, authorPredicate, categoryPredicate, keywordPredicate);
        };
    }
}