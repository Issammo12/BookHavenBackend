package org.example.pfa.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.pfa.dao.entities.User;
@Getter
@Setter
public class LoginResponse {
    private String token;
    private User user;

    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
