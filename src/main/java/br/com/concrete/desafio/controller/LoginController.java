package br.com.concrete.desafio.controller;

import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> user(@RequestBody User user){
        User loginUser = userService.login(user);
        return ResponseEntity.status(HttpStatus.OK).body(loginUser);
    }
}
