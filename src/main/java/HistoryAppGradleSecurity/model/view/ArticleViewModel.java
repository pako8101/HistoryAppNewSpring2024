package HistoryAppGradleSecurity.model.view;

import HistoryAppGradleSecurity.model.enums.PeriodEnum;

public class ArticleViewModel {

    private  Long id;

    private String title;
    private String author;

    private String pictureUrl;
    private PeriodEnum period;
    private String content;

    public ArticleViewModel() {

    }

    public ArticleViewModel(Long id, String title, String author, String pictureUrl, PeriodEnum period, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pictureUrl = pictureUrl;
        this.period = period;
        this.content = content;
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

    public String getAuthor() {
        return author;
    }

    public ArticleViewModel setAuthor(String author) {
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
