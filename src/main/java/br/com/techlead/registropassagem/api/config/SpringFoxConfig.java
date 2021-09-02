package br.com.techlead.registropassagem.api.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.techlead.registropassagem.api.controller"))
					.paths(PathSelectors.any())
					.build()
				.apiInfo(apiInfo())
				.tags(new Tag("RegistroPassagem", "Gerencia a relização do registro de passagem via API", 1))
				.tags(new Tag("Registrador", "Gerencia os dispostivos que realizam registro de passagem (POSTOS, ANTENAS) etc", 2))
				.tags(new Tag("Alertas", "Gerencia a relização de alertas de monitoramento", 4))
				.tags(new Tag("Integradores", "Gerencia rotinas e servicos de integração no fluxo do registro de passagem", 3))
				.tags(new Tag("Monitoramento", "Gerencia as medições e gatilhos dos alertas monitorados", 5))
				.tags(new Tag("Paineis", "Gerencia paineis de dados apresentados", 6))
				
				;
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("REGISTRO DE PASSAGEM API")
				.description("API para registro de passagem")
				.version("1")
				.contact(new Contact("TECHLEAD", "https://www.techlead.com", "techlead@techlead.com"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}