package br.com.concrete.desafio.controller;

import br.com.concrete.desafio.dto.request.UserRequestDTO;
import br.com.concrete.desafio.dto.response.UserResponseDTO;
import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.service.UserService;
import org.modelmapper.ModelMapper;
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
    public ResponseEntity<UserResponseDTO> user(@RequestBody UserRequestDTO dto) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(dto, User.class);
        User createUser = userService.save(user);
        UserResponseDTO userResponse = mapper.map(createUser, UserResponseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> profile(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        User validUser = userService.validUser(id, token);
        return ResponseEntity.status(HttpStatus.OK).body(validUser);
    }
}
