package br.com.concrete.desafio.controller;

import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> user(@RequestBody User user) {
        User createUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

}
