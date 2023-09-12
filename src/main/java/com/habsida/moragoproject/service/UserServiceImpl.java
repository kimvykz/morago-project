package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.CreateUserInput;
import com.habsida.moragoproject.model.input.RegistrationUserInput;
import com.habsida.moragoproject.model.input.UpdateUserInput;
import com.habsida.moragoproject.repository.RoleRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl (UserRepository userRepository,
                            RoleRepository roleRepository,
                            ModelMapper modelMapper,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    private List<Role> defineIdForExistingRoles (List<Role> roles) {
        List<Role> userRoles = new ArrayList<>();
        for(Role role : roles) {
            if (role.getId() == null){
                Optional<Role> existRole = roleRepository.findByName(role.getName());
                if (existRole.isPresent()) {
                    userRoles.add(existRole.get());
                } else {
                    userRoles.add(role);
                }
            } else {
                userRoles.add(role);
            }
        }
        return userRoles;
    }
    @Override
    public List<User> getAll () {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllPaged (PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public User getById (Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("User is not found for the id - " + id);
        });
    }

    @Override
    public User create (CreateUserInput createUserInput) {

        User user = modelMapper.map(createUserInput, User.class);


        if (isExistsByPhone(user.getPhone())) {
            throw new KeyAlreadyExistsException("User is already existed with phone - "
                    + user.getPhone());
        }

        if (user.getFirstName() == null ) {
            throw new IllegalArgumentException("field firstName cannot be null");
        }
        if (user.getLastName() == null ) {
            throw new IllegalArgumentException("field lastName cannot be null");
        }
        if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("field phone cannot be Empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("field password cannot be Empty");
        }
        if (user.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        }
        if (user.getIsDebtor() == null) {
            throw new IllegalArgumentException("field isDebtor cannot be null");
        }
        if (user.getRatings() == null) {
            throw new IllegalArgumentException("field rating cannot be null");
        }
        if (user.getTotalRatings() == null) {
            throw new IllegalArgumentException("field totalRatings cannot be null");
        }
        if (user.getBalance() == null) {
            throw new IllegalArgumentException("field balance cannot be null");
        }
        if (user.getOnBoardingStatus() == null) {
            throw new IllegalArgumentException("field getOnBoardingStatus cannot be null");
        }
        if (user.getApnToken() == null) {
            //user.setApnToken("token");  //here I need to know how to process this field
        }
        if (user.getFcmToken() == null) {
            //user.setFcmToken("token");  //here I need to know how to process this field
        }
        if (user.getRoles() == null) {
            throw new IllegalArgumentException("User must have at least 1 role");
        } else if (user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
                .containsAll(Arrays.asList(ERole.ROLE_USER, ERole.ROLE_TRANSLATOR))) {
            throw new IllegalArgumentException("User cannot have roles ROLE_USER and ROLE_TRANSLATOR at the same time");
        }
        if (user.getUserProfile() == null) {
            //here I need to know logic
        }
        if (user.getTranslatorProfile() == null) {
            //here I need to know logic
        }

        user.setRoles(defineIdForExistingRoles(user.getRoles()));
        return userRepository.save(user);
    }

    @Override
    public User update (UpdateUserInput updateUserInput) {

        User user = getById(updateUserInput.getId());
        modelMapper.map(updateUserInput, user);

        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("field firstName cannot be Empty");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("field lastName cannot be Empty");
        }
        if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("field phone cannot be Empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("field password cannot be Empty");
        }
        if (user.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        }
        if (user.getIsDebtor() == null) {
            throw new IllegalArgumentException("field isDebtor cannot be null");
        }
        if (user.getRatings() == null) {
            throw new IllegalArgumentException("field rating cannot be null");
        }
        if (user.getTotalRatings() == null) {
            throw new IllegalArgumentException("field totalRatings cannot be null");
        }
        if (user.getBalance() == null) {
            throw new IllegalArgumentException("field balance cannot be null");
        }
        if (user.getOnBoardingStatus() == null) {
            throw new IllegalArgumentException("field getOnBoardingStatus cannot be null");
        }
        if (user.getApnToken() == null) {
            //user.setApnToken("token");  //here I need to know how to process this field
        }
        if (user.getFcmToken() == null) {
            //user.setFcmToken("token");  //here I need to know how to process this field
        }
        if (user.getRoles() == null) {
            throw new IllegalArgumentException("User must have at least 1 role");
        } else if (user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
                .containsAll(Arrays.asList(ERole.ROLE_USER, ERole.ROLE_TRANSLATOR))) {
            throw new IllegalArgumentException("User cannot have roles ROLE_USER and ROLE_TRANSLATOR at the same time");
        }
        if (user.getUserProfile() == null) {
            //here I need to know logic
        }
        if (user.getTranslatorProfile() == null) {
            //here I need to know logic
        }

        user.setRoles(defineIdForExistingRoles(user.getRoles()));

        return userRepository.save(user);
    }

    @Override
    public Boolean deleteById (Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public User getByPhone (String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Boolean isExistsByPhone (String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public User signUpUser(RegistrationUserInput registrationUserInput) {
        if (isExistsByPhone(registrationUserInput.getPhone())) {
            throw new IllegalArgumentException("User is already existed with phone - " + registrationUserInput.getPhone());
        }
        if (registrationUserInput.getPhone() == null || registrationUserInput.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null");
        }
        if (registrationUserInput.getPassword() == null ) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        User user = modelMapper.map(registrationUserInput, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setBalance(0.0f);
        user.setFirstName("");
        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setLastName("");
        user.setOnBoardingStatus(0);
        user.setRatings(0.0);
        user.setTotalRatings(0);

        return userRepository.save(user);
    }
}
