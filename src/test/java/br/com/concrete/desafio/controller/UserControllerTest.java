package br.com.concrete.desafio.controller;

import br.com.concrete.desafio.model.Phone;
import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void retornoStatusCode201QuandoSalvoComSucesso() throws Exception {
        String userJSON = "{\n" +
                "        \"name\": \"João da Silva\",\n" +
                "        \"email\": \"joao@silva.org\",\n" +
                "        \"password\": \"hunter2\",\n" +
                "        \"phones\": [\n" +
                "            {\n" +
                "                \"number\": \"987654321\",\n" +
                "                \"ddd\": \"21\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        Phone phone = new Phone();
        phone.setNumber("2222-3333");
        phone.setDdd("91");
        ArrayList<Phone> phoneList = new ArrayList<>();
        phoneList.add(phone);

        User user = new User();
        user.setName("João da Silva");
        user.setEmail("joao@silva.org");
        user.setPassword("hunter2");
        user.setPhones(phoneList);

        Mockito.when(userService.save(any())).thenReturn(user);

        MockHttpServletRequestBuilder request = post("/users")
                .content(userJSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("João da Silva"))
                .andExpect(jsonPath("$.email").value("joao@silva.org"));

    }

}
