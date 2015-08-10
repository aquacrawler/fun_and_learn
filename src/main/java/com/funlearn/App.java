package com.funlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
            .addResourceHandler("/**/*")
            .addResourceLocations("classpath:web/"); 
        
//        registry
//            .addResourceHandler("/**/*.css")
//            .addResourceLocations("classpath:web/");
//        
//        registry
//            .addResourceHandler("/**/*.js")
//            .addResourceLocations("classpath:web/");
                
    }
    
    public static void main(String[] args) throws Exception {
            SpringApplication.run(App.class, args);
    }
}