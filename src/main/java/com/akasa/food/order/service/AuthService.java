package com.akasa.food.order.service;


import com.akasa.food.order.dto.*;
import com.akasa.food.order.exceptions.JwtTokenIncorrectStructureException;
import com.akasa.food.order.exceptions.JwtTokenMalformedException;
import com.akasa.food.order.exceptions.JwtTokenMissingException;
import com.akasa.food.order.models.UserRole;
import com.akasa.food.order.repository.RoleRepo;
import com.akasa.food.order.repository.UserRepo;
import com.akasa.food.order.models.User;
import com.akasa.food.order.utils.JwtTokenUtil;
import com.akasa.food.order.utils.builders.UserBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CartService cartService;

    public ResponseEntity<?> authenticate(AuthRequest authRequest, String sessionId) {
        User user = null;
        try {
            user = authenticate(authRequest.getUsername(), authRequest.getPassword(), authRequest.getEmail());
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ErrorResponse.builder()
                            .status("401")
                            .error(e.getMessage())
                            .message("Failed")
                            .build());
        }
        // generating token
        JwtTokenDto jwtTokenDto = null;
        try {
            jwtTokenUtil.validateToken(user.getToken());
            jwtTokenDto = new JwtTokenDto(user.getToken(), "200", user.getId().toString());

        } catch (Exception e) {
            log.info("Token expired. Generating new token.");
            HashSet<String> objects = user.getRoles().stream().map(UserRole::getName).collect(Collectors.toCollection(HashSet::new));
            String token = jwtTokenUtil.generateToken(authRequest.getEmail(), objects);
            user.setToken(token);
            userRepo.save(user);
            jwtTokenDto = new JwtTokenDto(token,"200",user.getId().toString());
        }
        cartService.updateCart(sessionId, user.getUsername());
        return ResponseEntity.ok(jwtTokenDto);
    }

    private User authenticate(String username, String password, String email) {
        Optional<User> user;

        if (!StringUtils.isBlank(email.trim())) {
            user = userRepo.findByEmail(email.trim().toLowerCase());
        } else {
            throw new RuntimeException("Username or email is mandatory.");
        }
        if (user.isEmpty()) {
            throw new RuntimeException("Invalid credentials.");
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new RuntimeException("Invalid credentials.");
        }
        return user.get();
    }

    public ResponseEntity<?> validate(String header) {
        String[] parts = header.split(" ");
        if (parts.length != 2 || !"Bearer".equals(parts[0])) {
            throw new JwtTokenIncorrectStructureException("Incorrect Authentication Structure");
        }
        try {
            jwtTokenUtil.validateToken(parts[1]);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ErrorResponse.builder().status("401").message(e.getMessage()));
        }
    }

    public ResponseEntity<?> signup(RegisterRequest request, String sessionId) {
        Optional<User> userOptional = userRepo.findByUsername(request.getEmail());
        if (userOptional.isPresent()) {
            return ResponseEntity
                    .ok(
                            ErrorResponse.builder()
                                    .error("User already present with same email.")
                                    .message("User already present with same email.")
                                    .status("400")
                                    .build()
                    );
        }
        if (validatePassword(request)) {
            String encode = passwordEncoder.encode(request.getPassword());
            UserRole role = roleRepo.findById(1L).get();
            Set<UserRole> roles= new HashSet<>();
            roles.add(role);
            User user = UserBuilder.builder()
                    .password(encode)
                    .email(request.getEmail().toLowerCase())
                    .username(request.getEmail().toLowerCase())
                    .roles(roles)
                    .accountNonExpired(true)
                    .build();

            userRepo.save(user);
            AuthRequest authRequest = AuthRequest.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(request.getPassword())
                    .build();
            return authenticate(authRequest, sessionId);
        }
        return ResponseEntity
                .ok(
                        ErrorResponse.builder()
                                .error("Password and confirm Password should match")
                                .message("failed")
                                .status("400")
                                .build()
                );

    }

    public boolean validatePassword(RegisterRequest validateUserInfo) {
        return validateUserInfo.getPassword().equals(validateUserInfo.getConfirmPassword());
    }

    public ResponseEntity<?> validateUsername(String username) {
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(
                    ErrorResponse.builder()
                            .message("Email already used by some other user")
                            .status("400")
                            .build()
            );
        }
        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("Success")
                        .status("200")
                        .build()
                );
    }

    public ResponseEntity<?> update(User user) {
        Optional<User> userOptional = userRepo.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();
            //user1.getRoles().add(user.getRoles());
            User saved = userRepo.save(user1);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    public User getUser(String id) {
        log.info("gettong user");
        return userRepo.findByUsername(id).get();
    }
}
