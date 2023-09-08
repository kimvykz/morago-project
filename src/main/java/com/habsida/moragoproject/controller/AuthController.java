package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.auth.AuthResponse;
import com.habsida.moragoproject.model.auth.LoginPassInput;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.RegisterNewUserInput;
import com.habsida.moragoproject.security.JwtGenerator;
import com.habsida.moragoproject.service.RoleService;
import com.habsida.moragoproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private JwtGenerator jwtGenerator;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          RoleService roleService,
                          PasswordEncoder passwordEncoder,
                          ModelMapper modelMapper,
                          JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginPassInput loginPassInput) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginPassInput.getPhone(),
                        loginPassInput.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }

    @PostMapping(path = "registration")
    @ResponseBody
    public User registerNewUser (@RequestBody RegisterNewUserInput registerNewUserInput) {

        User user = modelMapper.map(registerNewUserInput, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.register(user);

    }
}
