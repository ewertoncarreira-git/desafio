package br.com.concrete.desafio;

import br.com.concrete.desafio.model.Phone;
import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

//	@Bean
	/*public CommandLineRunner init(@Autowired UserService users) {
		return args -> {
			System.out.println("Salvando Users");

			User user = new User(
					"Ewerton"
					,"ewerton.l.carreira@accenture.com"
					,"123456"
					,LocalDateTime.now()
					,LocalDateTime.now()
					,LocalDateTime.now()
					,"123456"
			);

			Phone phone = new Phone();
			phone.setNumber("2222-3333");
			phone.setDdd("91");
			ArrayList<Phone> phoneList = new ArrayList<>();
			phone.setUser(user);
			phoneList.add(phone);

			user.setPhones(phoneList);

			users.save(user);

		};
	}*/
}
