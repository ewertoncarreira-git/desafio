package br.com.concrete.desafio.service;

import br.com.concrete.desafio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService  {
        User save(User user);

        User login(User user);

        User validUser(Long id, String token);
}
