package com.shoppingcart.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String prefix =  WebConfig.class.getResource("/").toString();
        logger.info("!!!!!!!!!!!!!!!!!!!!!! path is {}", prefix);
//        int index = prefix.indexOf("target/classes/");
//        prefix = prefix.substring(0, index);
//        String path = prefix + "src/main/resources/static/media/";
        registry.addResourceHandler("/media/**")
                .addResourceLocations("/app/src/main/resources/static/media/");
    }

}
