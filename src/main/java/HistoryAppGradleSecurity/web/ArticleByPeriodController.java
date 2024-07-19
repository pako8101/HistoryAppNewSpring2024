package HistoryAppGradleSecurity.web;

import HistoryAppGradleSecurity.model.enums.PeriodEnum;
import HistoryAppGradleSecurity.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleByPeriodController {
    private final ArticleService articleService;

    public ArticleByPeriodController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/{period}")
    public ModelAndView getArticlesByPeriod(@PathVariable PeriodEnum period) {
        String view = "";
        switch (period){
            case EGYPT -> view ="egypt";
            case ANCIENT_GREECE -> view ="greece";
            case ROME_EMPIRE -> view ="rome";
            case STONE_AGE -> view ="stone";
            case MESOPOTAMIA -> view ="mesopotamia";
            case CENTRAL_AMERICA -> view ="america";
            case ANDEAN_REGION -> view ="andean";
        }

        ModelAndView modelAndView = new ModelAndView(view);

        modelAndView.addObject("articles",
                articleService.getArticleByPeriod(period));

        return modelAndView;
    }

    @GetMapping("/articlesByPeriods/stoneAge")
    public String stone(){
        return "articlesByPeriods/stoneAge";
    }
    @GetMapping("/articlesByPeriods/andian")
    public String andian(){
        return "articlesByPeriods/andian";
    }
    @GetMapping("/articlesByPeriods/america")
    public String america(){
        return "articlesByPeriods/america";
    }
    @GetMapping("/articlesByPeriods/egypt")
    public String egypt(){
        return "articlesByPeriods/egypt";
    }
    @GetMapping("/articlesByPeriods/greece")
    public String greece(){
        return "articlesByPeriods/greece";
    }
    @GetMapping("/articlesByPeriods/mesopotamia")
    public String mesopotamia(){
        return "articlesByPeriods/mesopotamia";
    }
    @GetMapping("/articlesByPeriods/rome")
    public String rome(){
        return "articlesByPeriods/rome";
    }
}
