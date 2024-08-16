package com.akasa.food.order.controller;


import com.akasa.food.order.dto.RegisterRequest;
import com.akasa.food.order.models.User;
import com.akasa.food.order.service.AuthService;
import com.akasa.food.order.dto.AuthRequest;
import com.akasa.food.order.dto.JwtTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    private static final String AUTHORIZATION = "Authorization";


    @GetMapping("/test")
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest){
        return authService.authenticate(authRequest);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody JwtTokenDto jwtTokenDto){
        return authService.validate(jwtTokenDto.getToken());
    }

    @PostMapping("/register")
    public ResponseEntity<?> signup(@RequestBody RegisterRequest user){
        return authService.signup(user);
    }

    @GetMapping("/sign-in/user/{id}")
    public User getUser(@PathVariable String id){
        return  authService.getUser(id);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUer(@RequestBody User user){
        return authService.update(user);
    }

    @PostMapping("/validate-username")
    public ResponseEntity<?> validateUsername(@RequestParam String username){
        return authService.validateUsername(username);
    }
}
