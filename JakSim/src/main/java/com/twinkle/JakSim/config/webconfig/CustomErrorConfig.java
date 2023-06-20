package com.twinkle.JakSim.config.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CustomErrorConfig implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/error").setViewName("error/error");
//        registry.addViewController("/find").setStatusCode(HttpStatus.FORBIDDEN).setViewName("error/forbidden");
//    }
}
