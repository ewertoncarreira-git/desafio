package br.com.concrete.desafio.service;

import br.com.concrete.desafio.handler.UserNotFoundException;
import br.com.concrete.desafio.model.Phone;
import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("E-mail já existente");
        }

        for (Phone phone : user.getPhones()) {
            phone.setUser(user);
        }

        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLast_login(LocalDateTime.now());
        user.setToken(UUID.randomUUID().toString());

        return userRepository.save(user);
    }

    @Override
    public User login(User user) {
        User userByEmail = userRepository.findByEmail(user.getEmail());

        if (userByEmail == null || !user.getPassword().equals(userByEmail.getPassword())) {
            throw new UserNotFoundException("Usuário e/ou senha inválidos");
        }

        userByEmail.setLast_login(LocalDateTime.now());
        userByEmail.setToken(UUID.randomUUID().toString());

        return  userRepository.save(userByEmail);
    }
}
