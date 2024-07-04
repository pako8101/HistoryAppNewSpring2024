package HistoryAppGradleSecurity.service;

import HistoryAppGradleSecurity.model.binding.UploadPictureArticleBindingModel;
import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import HistoryAppGradleSecurity.model.service.ArticleServiceModel;
import HistoryAppGradleSecurity.model.view.ArticleCategoryViewModel;
import HistoryAppGradleSecurity.model.view.ArticleDetailsViewModel;
import HistoryAppGradleSecurity.model.view.ArticleViewModel;

import java.util.List;

public interface ArticleService {
    List<ArticleViewModel> findAllArticlesView();

    void addNewArticle(ArticleServiceModel articleServiceModel);

    ArticleDetailsViewModel findArticleBId(Long id);

    Article findArticleById(Long id);

    ArticleDetailsViewModel getDetails(Long id);

    void delete(Long id);

    void uploadPicture(UploadPictureArticleBindingModel uploadPictureArticleBindingModel);

    List<ArticleCategoryViewModel> getAllByCategory(CategoryNameEnum categoryName);
}
