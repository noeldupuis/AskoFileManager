package fr.noeldupuis.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	private static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) throws UnknownHostException {

		SpringApplication app = new SpringApplication(DemoApplication.class);
		Environment env = app.run(args).getEnvironment();
		log.info("Swagger is running on: http://{}:{}/swagger-ui.html",
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));

	}

	@Bean
	public Docket simpleDiffServiceApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("asko")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/");

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("A file service for tke Asko association")
				.description("Simple REST service for giving files to Asko")
				.contact(new Contact("Dupuis Noël", "http://github.con/noeldupuis", "contact@noeldupuis.fr"))
				.version("0.0.1")
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
