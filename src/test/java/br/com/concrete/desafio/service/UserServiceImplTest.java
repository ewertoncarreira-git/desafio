package br.com.concrete.desafio.service;

import br.com.concrete.desafio.handler.InvalidEmailException;
import br.com.concrete.desafio.handler.InvalidNameException;
import br.com.concrete.desafio.handler.InvalidPasswordException;
import br.com.concrete.desafio.handler.UserNotFoundException;
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
                , "ewerton@carreira.com"
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
    public void deveLancarInvalidEmailExceptionQuandoEmailForNulo() {
        Phone phone = new Phone();
        phone.setDdd("91");
        phone.setNumber("2222-3333");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User(
                "Ewerton Carreira"
                , null
                , "hunter2"
                , LocalDateTime.now()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , phoneList
                , "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGH"
        );
        user.setId(1L);

        Assertions.assertThrows(InvalidEmailException.class, () -> {
            userService.save(user);
        });
    }

    @Test
    public void deveLancarInvalidEmailExceptionQuandoEmailForVazio() {
        Phone phone = new Phone();
        phone.setDdd("91");
        phone.setNumber("2222-3333");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User(
                "Ewerton Carreira"
                , ""
                , "hunter2"
                , LocalDateTime.now()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , phoneList
                , "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGH"
        );
        user.setId(1L);

        Assertions.assertThrows(InvalidEmailException.class, () -> {
            userService.save(user);
        });
    }

    @Test
    public void deveLancarInvalidNameExceptionQuandoNomeForNulo() {
        Phone phone = new Phone();
        phone.setDdd("91");
        phone.setNumber("2222-3333");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User(
                null
                , "ewerton@carreira.com"
                , "hunter2"
                , LocalDateTime.now()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , phoneList
                , "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGH"
        );
        user.setId(1L);

        Assertions.assertThrows(InvalidNameException.class, () -> {
            userService.save(user);
        });
    }

    @Test
    public void deveLancarInvalidNameExceptionQuandoNomeForVazio() {
        Phone phone = new Phone();
        phone.setDdd("91");
        phone.setNumber("2222-3333");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User(
                ""
                , "ewerton@carreira.com"
                , "hunter2"
                , LocalDateTime.now()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , phoneList
                , "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGH"
        );
        user.setId(1L);

        Assertions.assertThrows(InvalidNameException.class, () -> {
            userService.save(user);
        });
    }

    @Test
    public void deveLancarInvalidPasswordExceptionQuandoSenhaForNulo() {
        Phone phone = new Phone();
        phone.setDdd("91");
        phone.setNumber("2222-3333");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User(
                "Ewerton Carreira"
                , "ewerton@carreira.com"
                , null
                , LocalDateTime.now()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , phoneList
                , "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGH"
        );
        user.setId(1L);

        Assertions.assertThrows(InvalidPasswordException.class, () -> {
            userService.save(user);
        });
    }

    @Test
    public void deveLancarInvalidPasswordExceptionQuandoSenhaForVazio() {
        Phone phone = new Phone();
        phone.setDdd("91");
        phone.setNumber("2222-3333");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User(
                "Ewerton Carreira"
                , "ewerton@carreira.com"
                , ""
                , LocalDateTime.now()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , phoneList
                , "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGH"
        );
        user.setId(1L);

        Assertions.assertThrows(InvalidPasswordException.class, () -> {
            userService.save(user);
        });
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
                , "ewerton@carreira.com"
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

    @Test
    public void deveLancarUserNotFoundExceptionQuandoTokenNaoCadastrado() {
        User user = new User();
        user.setId(1L);
        user.setToken("12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGHS");

        Mockito.when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user));

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.validUser(user.getId(), "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjG");
        });
    }

    @Test
    public void deveLancarUserNotFoundExceptionQuandoSessaoInvalida() {
        User user = new User();
        user.setId(1L);
        user.setLastLogin(LocalDateTime.parse("2020-10-21T14:15:50.239"));
        user.setToken("12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGHS");

        Mockito.when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user));

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.validUser(user.getId(), "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjG");
        });
    }

    @Test
    public void deveLancarUserNotFoundExceptionQuandoTokenNaoExiste() {
        User user = new User();
        user.setId(1L);
        user.setToken("12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGHS");

        Mockito.when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user));

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.validUser(user.getId(), "");
        });
    }

    @Test
    public void deveValidarProfileOk() {
        Phone phone = new Phone();
        phone.setDdd("91");
        phone.setNumber("2222-3333");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User(
                "Ewerton Carreira"
                , "ewerton.l.carreira@.com"
                , "hunter2"
                , LocalDateTime.now()
                , LocalDateTime.now()
                , LocalDateTime.now()
                , phoneList
                , "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGH"
        );
        user.setId(1L);

        Mockito.when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user));

        User validatedUser = userService.validUser(1L, "12345776iuoidjjhjdgsduyfib7868776%&$%#nkjGH");

        assertEquals("Ewerton Carreira", validatedUser.getName());
    }

    @Test
    public void deveLancarUserNotFoundExceptionQuandoEmailNaoExistir() {
        Mockito.when(userRepository.findByEmail(any())).thenReturn(null);

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.login("email1a@com", "hunted2");
        });
    }

    @Test
    public void deveLancarUserNotFoundExceptionQuandoSenhaNaoBater() {
        User mockedUser = new User();
        mockedUser.setId(2L);
        mockedUser.setEmail("email1a@com");
        mockedUser.setPassword("hunted2");

        Mockito.when(userRepository.findByEmail(any())).thenReturn(mockedUser);

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.login("email1a@com", "hunted2");
        });
    }
}