package com.energizeglobal.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@SpringBootApplication(scanBasePackages = {
        "com.energizeglobal.common",
        "com.energizeglobal.web",
        "com.energizeglobal.service",
        "com.energizeglobal.rest"
})
@ImportResource("classpath*:/config/i18.xml")
public class BooksDemoWeb extends SpringBootServletInitializer {

    /**
     * in case of application is deployed under traditional server
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BooksDemoWeb.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BooksDemoWeb.class, args);
    }

    @Bean()
    public ServletContextTemplateResolver templateResolver() {
        final ServletContextTemplateResolver resolver =
                new ServletContextTemplateResolver();
//        resolver.setPrefix("/WEB-INF/templates/");
//        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

}
