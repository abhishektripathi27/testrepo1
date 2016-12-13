package com.example.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(paths())
				.build().pathMapping("/").securitySchemes(Lists.<SecurityScheme> newArrayList(apiKey()))
				.securityContexts(Lists.newArrayList(securityContext())).apiInfo(apiInfo());
	}

	Predicate<String> paths() {
		return Predicates.or(PathSelectors.ant("/publish/**"));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(paths()).build();
	}

	List<SecurityReference> defaultAuth() {
		return Lists.newArrayList(new SecurityReference("My Security", new AuthorizationScope[0]));
	}

	private StringBuilder readSwaggerDocument() {
		StringBuilder builder = new StringBuilder();

		builder.append("This is test documentation.");
		return builder;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Audit Event API").description(readSwaggerDocument().toString())
				.version("1.0").contact(new Contact("Abhishek Tripathi", "", "")).build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "X-DFS-JWT", "header");
	}

	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration(null);
	}

}
