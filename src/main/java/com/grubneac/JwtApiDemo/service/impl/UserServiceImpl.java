package com.grubneac.JwtApiDemo.service.impl;

import com.grubneac.JwtApiDemo.model.Role;
import com.grubneac.JwtApiDemo.model.Status;
import com.grubneac.JwtApiDemo.model.User;
import com.grubneac.JwtApiDemo.repository.RoleRepository;
import com.grubneac.JwtApiDemo.repository.UserRepository;
import com.grubneac.JwtApiDemo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        user.setRoles(List.of(roleUser));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.info("IN register user: {} successfully registeredUser", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("IN getAll - {} users found",users.size());
        return users;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("IN findById - no user found by id: {}",  id);
        } else {
            log.info("IN findById - user: {} found by id: {}", result, id);
        }
        return result;
    }

    @Override
    public void delete(Long id) {
            userRepository.deleteById(id);
            log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
