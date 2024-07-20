package HistoryAppGradleSecurity.repository;

import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

    List<Article> findAllByCategories_Name(CategoryNameEnum categoryName);

    Optional<Article> findByIdAndAuthor(Long id, UserEnt user);

    Optional<Article> findTopByOrderByCreatedDesc();

    List<Article> findAllByPeriod(PeriodEnum period);




    // findTopByOrderByCreatedOnDesc
}
