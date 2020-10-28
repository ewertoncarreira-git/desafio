package br.com.concrete.desafio.service;

import br.com.concrete.desafio.model.User;

public interface UserService  {
        User save(User user);

        User login(User user);

        User validUser(Long id, String token);
}
