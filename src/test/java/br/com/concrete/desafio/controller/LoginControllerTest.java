package br.com.concrete.desafio.controller;

import br.com.concrete.desafio.dto.request.LoginRequestDTO;
import br.com.concrete.desafio.model.Phone;
import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class LoginControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void retornoStatusCode200QuandoLogadoComSucesso() throws Exception {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("joao@silva.org");
        loginRequestDTO.setPassword("hunter2");

        Phone phone = new Phone();
        phone.setNumber("2222-3333");
        phone.setDdd("91");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User();
        user.setId(1L);
        user.setName("João da Silva");
        user.setEmail("joao@silva.org");
        user.setPassword("hunter2");
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(UUID.randomUUID().toString());
        user.setPhones(phoneList);

        Mockito.when(userService.login(any(), any())).thenReturn(user);

        MockHttpServletRequestBuilder request = post("/login")
                .content(objectMapper.writeValueAsString(loginRequestDTO))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.last_login").isNotEmpty())
                .andExpect(jsonPath("$.token").value(user.getToken()));
    }
}
