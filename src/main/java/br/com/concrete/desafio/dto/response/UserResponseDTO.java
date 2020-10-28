package br.com.concrete.desafio.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDTO {
    private Long id;

    private String name;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime created;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime modified;

    @JsonProperty(value = "last_login")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime lastLogin;

    private String token;

    private List<PhoneResponseDTO> phones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<PhoneResponseDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneResponseDTO> phones) {
        this.phones = phones;
    }
}
