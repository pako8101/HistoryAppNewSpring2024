package HistoryAppGradleSecurity.model.view;

import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;

import java.util.Set;

public class ArticleViewModel {

    private  Long id;

    private String title;
    private UserEnt author;

    private String pictureUrl;
    private PeriodEnum period;
    private String content;
    private Set<CategoryNameEnum> categories;
    public ArticleViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ArticleViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public PeriodEnum getPeriod() {
        return period;
    }

    public ArticleViewModel setPeriod(PeriodEnum period) {
        this.period = period;
        return this;
    }

    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public ArticleViewModel setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
        return this;
    }

    public UserEnt getAuthor() {
        return author;
    }

    public ArticleViewModel setAuthor(UserEnt author) {
        this.author = author;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public ArticleViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleViewModel setContent(String content) {
        this.content = content;
        return this;
    }
}
