package com.grubneac.JwtApiDemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grubneac.JwtApiDemo.model.Status;
import com.grubneac.JwtApiDemo.model.User;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AdminUserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String status;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setStatus(Status.valueOf(status));

        return user;
    }
    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setUsername(user.getUsername());
        adminUserDto.setFirstname(user.getFirstname());
        adminUserDto.setLastname(user.getLastname());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setStatus(user.getStatus().name());

        return adminUserDto;
    }    
}
