package com.grubneac.JwtApiDemo.rest;

import com.grubneac.JwtApiDemo.dto.AdminUserDto;
import com.grubneac.JwtApiDemo.dto.UserDto;
import com.grubneac.JwtApiDemo.model.User;
import com.grubneac.JwtApiDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminRestControllerV1 {

    @Autowired
    private UserService userService;

    @GetMapping("users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User byId = userService.findById(id);
        if (byId == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AdminUserDto result = AdminUserDto.fromUser(byId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
