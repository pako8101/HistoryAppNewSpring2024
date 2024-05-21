package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.model.entity.Article;
import HistoryAppGradleSecurity.model.entity.LogEntity;
import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.service.LogServiceModel;
import HistoryAppGradleSecurity.model.view.ArticleDetailsViewModel;
import HistoryAppGradleSecurity.repository.LogRepository;
import HistoryAppGradleSecurity.service.ArticleService;
import HistoryAppGradleSecurity.service.LogService;
import HistoryAppGradleSecurity.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final ArticleService articleService;
    private final UserService userService;
    private final ModelMapper modelMapper;
@Autowired
    public LogServiceImpl(LogRepository logRepository, ArticleService articleService,
                          UserService userService, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.articleService = articleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createLog(String action, Long articleId) {

        Article article = articleService.findArticleById(articleId);

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        String username = authentication.getName();

        UserEnt userEnt = userService.findByName(username);

        LogEntity logEntity = new LogEntity()
                .setArticle(article)
                .setUserEnt(userEnt)
                .setAction(action)
                .setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);
    }

    @Override
    public List<LogServiceModel> findAllLogs() {
        return logRepository
                .findAll()
                .stream()
                .map(logEntity -> {
                    LogServiceModel logServiceModel =
                    modelMapper.map(logEntity,LogServiceModel.class);
                    logServiceModel.setArticle(logEntity.getArticle().getTitle())
                            .setUser(logEntity.getUserEnt().getUsername());

                    return logServiceModel;

                }).collect(Collectors.toList());
    }
}
