package HistoryAppGradleSecurity.model.service;

import HistoryAppGradleSecurity.model.entity.Picture;
import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.enums.CategoryNameEnum;
import HistoryAppGradleSecurity.model.enums.PeriodEnum;

import java.util.Set;

public class ArticleServiceModel {
    private Long id;


    private String title;
    private String author;
    private String content;
    private Set<Picture> pictures;
    private PeriodEnum period;
    private Set<CategoryNameEnum>categories;

    public ArticleServiceModel() {
    }

    public PeriodEnum getPeriod() {
        return period;
    }

    public ArticleServiceModel setPeriod(PeriodEnum period) {
        this.period = period;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleServiceModel setContent(String content) {
        this.content = content;
        return this;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public ArticleServiceModel setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public ArticleServiceModel setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ArticleServiceModel setId(Long id) {
        this.id = id;
        return this;
    }


    public String getTitle() {
        return title;
    }

    public ArticleServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ArticleServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }
}
