package HistoryAppGradleSecurity.repository;

import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    List<Article> findAllByCategories_Name(CategoryNameEnum categoryName);

}
