package com.compras.documentacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocumentacao {
	
	@Bean
	public Docket api() {
		
		//Adding header
		ParameterBuilder parameterBuilder = new ParameterBuilder();
		parameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false);
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(parameterBuilder.build());
		
		return new Docket(DocumentationType.SWAGGER_2)
						 .select()
						 .apis(RequestHandlerSelectors.basePackage("com.compras.resources"))
						 .paths(PathSelectors.any())
						 .build()
						 .globalOperationParameters(parameters)
						 .apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
	    return new ApiInfoBuilder()
	        .title("SISTEMA DE COMPRAS ONLINE")
	        .description("\"Está é uma documentação da api COMPRASONLINE.\"")
	        .version("0.0.1")
	        .build();
	  }
}
