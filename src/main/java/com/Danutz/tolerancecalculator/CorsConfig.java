package com.Danutz.tolerancecalculator;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(("http://localhost:4200"), ("http://localhost:4300"), ("https://navigatorqualityangularclient.azurewebsites.net"), ("https://navigatordevangularclient.azurewebsites.net"), ("https://navigatorprodangularclient.azurewebsites.net"), ("https://navigatorqualitylanguageangularclient.azurewebsites.net"), ("https://webnavigator.guehring.de")).allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH").allowCredentials(true).allowedHeaders("*");
            }
        };
    }
}
