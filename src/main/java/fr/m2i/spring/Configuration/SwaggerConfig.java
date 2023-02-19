package fr.m2i.spring.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Value("${info.project.name}")
    private String projectName;
    
    @Value("${info.project.desc}")
    private String projectDescription;
    
    @Value("${info.project.version}")
    private String projectVersion;
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.m2i.spring"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }
}
