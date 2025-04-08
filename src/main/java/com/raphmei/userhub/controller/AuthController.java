package com.raphmei.userhub.controller;

import com.raphmei.userhub.configuration.JwtUtils;
import com.raphmei.userhub.dto.LoginRequest;
import com.raphmei.userhub.dto.UserDTO;
import com.raphmei.userhub.entity.User;
import com.raphmei.userhub.repository.UserRepository;
import com.raphmei.userhub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
      User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email"));

       if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
       }
       String token = jwtUtils.generateToken(user);
       return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
