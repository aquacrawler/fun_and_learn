package com.funlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author kerwin
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App extends WebMvcConfigurerAdapter {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/*.html")
            .addResourceLocations("classpath:web/"); 
        
        registry
            .addResourceHandler("/css/**/*.css")
            .addResourceLocations("classpath:web/css/");
        
        registry
            .addResourceHandler("/js/**/*.js")
            .addResourceLocations("classpath:web/js/");
                
    }
    
    public static void main(String[] args) throws Exception {
            SpringApplication.run(App.class, args);
    }
}