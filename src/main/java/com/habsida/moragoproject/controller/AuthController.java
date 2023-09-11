package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.auth.AuthResponse;
import com.habsida.moragoproject.model.input.SignInUserInput;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.SignUpUserInput;
import com.habsida.moragoproject.model.payload.SignInPayload;
import com.habsida.moragoproject.security.JwtGenerator;
import com.habsida.moragoproject.service.RoleService;
import com.habsida.moragoproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
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

import javax.validation.Valid;

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

    @QueryMapping("signin")
    public SignInPayload signIn(@Argument(name = "signInUser") SignInUserInput signInUserInput) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInUserInput.getPhone(),
                        signInUserInput.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new SignInPayload(userService.getByPhone(signInUserInput.getPhone()), token);
    }

    @MutationMapping(name = "signup")
    public User signUp (@Valid @Argument(name = "signUpUser") SignUpUserInput signUpUserInput) {
        if (userService.isExistsByPhone(signUpUserInput.getPhone())) {
            throw new IllegalArgumentException("User is already existed with phone - " + signUpUserInput.getPhone());
        }
        if (signUpUserInput.getPhone() == null || signUpUserInput.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null");
        }
        if (signUpUserInput.getPassword() == null ) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        User user = modelMapper.map(signUpUserInput, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setBalance(0.0f);
        user.setFirstName("");
        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setLastName("");
        user.setOnBoardingStatus(0);
        user.setRatings(0.0);
        user.setTotalRatings(0);

        return userService.create(user);

    }
}
