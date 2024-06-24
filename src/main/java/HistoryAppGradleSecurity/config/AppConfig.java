package HistoryAppGradleSecurity.config;

import HistoryAppGradleSecurity.model.entity.Picture;
import HistoryAppGradleSecurity.model.view.PictureViewModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {

        final ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .createTypeMap(Picture.class, PictureViewModel.class)
                .addMappings(mapper -> mapper
                        .map(Picture::getUrl, PictureViewModel::setSrc))
                .addMappings(mapper -> mapper
                        .map(Picture::getTitle, PictureViewModel::setAlt));


        return modelMapper;
    }
}
