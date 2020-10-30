package br.com.concrete.desafio.service;

import br.com.concrete.desafio.handler.InvalidEmailException;
import br.com.concrete.desafio.handler.InvalidNameException;
import br.com.concrete.desafio.handler.InvalidPasswordException;
import br.com.concrete.desafio.handler.UserNotFoundException;
import br.com.concrete.desafio.model.Phone;
import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new InvalidNameException("Informe um nome válido!");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new InvalidPasswordException("Informe uma senha válida!");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new InvalidEmailException("Informe um e-mail válido!");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("E-mail já existente");
        }

        for (Phone phone : user.getPhones()) {
            phone.setUser(user);
        }

        user.setPassword(passwordEncoder(user.getPassword()));
        user.setToken(UUID.randomUUID().toString());

        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        User userByEmail = userRepository.findByEmail(email);

        if (userByEmail == null || !isValidPassword(password, userByEmail.getPassword())) {
            throw new UserNotFoundException("Usuário e/ou senha inválidos");
        }

        userByEmail.setToken(UUID.randomUUID().toString());

        return userRepository.save(userByEmail);
    }

    @Override
    public User validUser(Long id, String token) {
        Optional<User> userById = userRepository.findById(id);

        if (token.isEmpty() || !userById.isPresent() || !userById.get().getToken().equals(token)) {
            throw new UserNotFoundException("Não autorizado");
        }

        Duration durationSession = Duration.between(userById.get().getLastLogin(), LocalDateTime.now());

        if (durationSession.getSeconds() / 60 > 30) {
            throw new UserNotFoundException("Sessão inválida");
        }

        return userById.get();
    }

    private String passwordEncoder(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    private boolean isValidPassword(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}
