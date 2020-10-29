package br.com.concrete.desafio;

import br.com.concrete.desafio.model.Phone;
import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}
}
