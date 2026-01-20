package com.ltech.shortUrl.Dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRequestDto {
    public String username;
    public String password;
    public String email;
    public String cpf;
    
    public UserRequestDto(String username, String password, String email, String cpf) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.cpf = cpf;
    }
}
