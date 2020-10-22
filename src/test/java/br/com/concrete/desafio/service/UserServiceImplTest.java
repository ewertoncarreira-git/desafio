package br.com.concrete.desafio.service;

import br.com.concrete.desafio.model.Phone;
import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {UserServiceImpl.class})
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void deveValidarUsuarioRetornadoQuandoSalvoComSucesso() {
        Phone phone = new Phone();
        phone.setDdd("91");
        phone.setNumber("2222-3333");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User(
                "Ewerton Carreira"
                , "ewerton.l.carreira@accenture.com"
                , "hunter2"
                , LocalDateTime.now()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , phoneList
                , "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGH"
        );
        user.setId(1L);

        Mockito.when(userRepository.existsByEmail(any())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(user);

        User createdUser = userService.save(user);

        assertEquals("Ewerton Carreira", createdUser.getName());
    }

    @Test
    public void deveLancarRuntimeExceptionQuandoEmailJaCadastrado() {
        Phone phone = new Phone();
        phone.setDdd("91");
        phone.setNumber("2222-3333");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User(
                "Ewerton Carreira"
                , "ewerton.l.carreira@accenture.com"
                , "hunter2"
                , LocalDateTime.now()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , phoneList
                , "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGH"
        );
        user.setId(1L);

        Mockito.when(userRepository.existsByEmail(any())).thenReturn(true);

        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.save(user);
        });
    }

}