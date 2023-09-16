package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.*;
import com.habsida.moragoproject.repository.RoleRepository;
import com.habsida.moragoproject.repository.UserRepository;
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
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl (UserRepository userRepository,
                            RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
    public Page<User> getAllByPaging (PageRequest pageRequest) {
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

        User user = new User();

        if (isExistsByPhone(user.getPhone())) {
            throw new KeyAlreadyExistsException("User is already existed with phone - "
                    + user.getPhone());
        }

        if (createUserInput.getFirstName() == null) {
            throw new IllegalArgumentException("field firstName cannot be null");
        } else {
            user.setFirstName(createUserInput.getFirstName());
        }
        if (createUserInput.getLastName() == null) {
            throw new IllegalArgumentException("field lastName cannot be null");
        } else {
            user.setLastName(createUserInput.getLastName());
        }
        if (createUserInput.getPhone() == null
                || createUserInput.getPhone().isBlank()) {
            throw new IllegalArgumentException("field phone cannot be Empty");
        } else {
            user.setPhone(createUserInput.getPhone());
        }
        if (createUserInput.getPassword() == null
                || createUserInput.getPassword().isBlank()) {
            throw new IllegalArgumentException("field password cannot be Empty");
        } else {
            user.setPassword(passwordEncoder.encode(createUserInput.getPassword()));
        }
        if (createUserInput.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        } else {
            user.setIsActive(createUserInput.getIsActive());
        }
        if (createUserInput.getIsDebtor() == null) {
            throw new IllegalArgumentException("field isDebtor cannot be null");
        } else {
            user.setIsDebtor(createUserInput.getIsDebtor());
        }
        if (createUserInput.getRatings() == null) {
            throw new IllegalArgumentException("field rating cannot be null");
        } else {
            user.setRatings(createUserInput.getRatings());
        }
        if (createUserInput.getTotalRatings() == null) {
            throw new IllegalArgumentException("field totalRatings cannot be null");
        } else {
            user.setTotalRatings(createUserInput.getTotalRatings());
        }
        if (createUserInput.getBalance() == null) {
            throw new IllegalArgumentException("field balance cannot be null");
        } else {
            user.setBalance(createUserInput.getBalance());
        }
        if (createUserInput.getOnBoardingStatus() == null) {
            throw new IllegalArgumentException("field getOnBoardingStatus cannot be null");
        } else {
            user.setOnBoardingStatus(createUserInput.getOnBoardingStatus());
        }
        if (createUserInput.getApnToken() == null) {
            //user.setApnToken("token");  //here I need to know how to process this field
        } else {
            user.setApnToken(createUserInput.getApnToken());
        }
        if (createUserInput.getFcmToken() == null) {
            //user.setFcmToken("token");  //here I need to know how to process this field
        } else {
            user.setFcmToken(createUserInput.getFcmToken());
        }
        if (createUserInput.getRoles() == null) {
            throw new IllegalArgumentException("User must have at least 1 role");
        }  else {
            user.setRoles(createUserInput.getRoles());
        }
        if (createUserInput.getUserProfile() == null) {
            //here I need to know logic
        } else {
            user.setUserProfile(createUserInput.getUserProfile());
        }
        if (createUserInput.getTranslatorProfile() == null) {
            //here I need to know logic
        } else {
            user.setTranslatorProfile(createUserInput.getTranslatorProfile());
        }

        user.setRoles(defineIdForExistingRoles(user.getRoles()));
        return userRepository.save(user);
    }

    @Override
    public User update (UpdateUserInput updateUserInput) {

        User user = getById(updateUserInput.getId());

        if (updateUserInput.getFirstName() != null
           && !updateUserInput.getFirstName().isBlank()
            && !user.getFirstName().equals(updateUserInput.getFirstName())) {
            user.setFirstName(updateUserInput.getFirstName());
        }
        if (updateUserInput.getLastName() != null
            && !updateUserInput.getLastName().isBlank()
            && !user.getLastName().equals(updateUserInput.getLastName())) {
            user.setLastName(updateUserInput.getLastName());
        }
        if (updateUserInput.getPhone() != null
            && !updateUserInput.getPhone().isBlank()
            && !user.getPhone().equals(updateUserInput.getPhone())) {
            user.setPhone(updateUserInput.getPhone());
        }
        if (updateUserInput.getPassword() != null
            && !updateUserInput.getPassword().isBlank()
            && !user.getPassword().equals(passwordEncoder.encode(updateUserInput.getPassword()))) {
            user.setPassword(passwordEncoder.encode(updateUserInput.getPassword()));
        }
        if (updateUserInput.getIsActive() != null
            && !user.getIsActive().equals(updateUserInput.getIsActive())) {
            user.setIsActive(updateUserInput.getIsActive());
        }
        if (updateUserInput.getIsDebtor() != null
            && !user.getIsDebtor().equals(updateUserInput.getIsDebtor())) {
            user.setIsDebtor(updateUserInput.getIsDebtor());
        }
        if (updateUserInput.getRatings() != null
            && !user.getRatings().equals(updateUserInput.getRatings())) {
            user.setRatings(updateUserInput.getRatings());
        }
        if (updateUserInput.getTotalRatings() != null
            && !user.getTotalRatings().equals(updateUserInput.getTotalRatings())) {
            user.setTotalRatings(updateUserInput.getTotalRatings());
        }
        if (updateUserInput.getBalance() != null
            && !user.getBalance().equals(updateUserInput.getBalance())) {
            user.setBalance(updateUserInput.getBalance());
        }
        if (updateUserInput.getOnBoardingStatus() != null
            && !user.getOnBoardingStatus().equals(updateUserInput.getOnBoardingStatus())) {
            user.setOnBoardingStatus(updateUserInput.getOnBoardingStatus());
        }
        if (updateUserInput.getApnToken() != null
            && !user.getApnToken().equals(updateUserInput.getApnToken())) {
            user.setApnToken(updateUserInput.getApnToken());
        }
        if (updateUserInput.getFcmToken() != null
            && !user.getFcmToken().equals(updateUserInput.getFcmToken())) {
            user.setFcmToken(updateUserInput.getFcmToken());
        }
//        if (updateUserInput.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
//                .containsAll(Arrays.asList(ERole.ROLE_USER, ERole.ROLE_TRANSLATOR))) {
//            throw new IllegalArgumentException("User cannot have roles ROLE_USER and ROLE_TRANSLATOR at the same time");
//        }
//        if (updateUserInput.getRoles() != null
//                && !user.getRoles().equals(updateUserInput.getRoles())) {
//            user.setRoles(updateUserInput.getRoles());
//        }
//        if (updateUserInput.getUserProfile() != null
//            && !user.getUserProfile().equals(updateUserInput.getUserProfile())) {
//            user.setUserProfile(updateUserInput.getUserProfile());
//        }
//        if (updateUserInput.getTranslatorProfile() != null
//            && !user.getTranslatorProfile().equals(updateUserInput.getTranslatorProfile())) {
//            user.setTranslatorProfile(updateUserInput.getTranslatorProfile());
//        }

        //user.setRoles(defineIdForExistingRoles(user.getRoles()));

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
    public User updateRolesByUserId(UpdateUserRolesInput updateUserRolesInput) {
//        if (updateUserRolesInput.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
//                .containsAll(Arrays.asList(ERole.ROLE_USER, ERole.ROLE_TRANSLATOR))) {
//            throw new IllegalArgumentException("User cannot have roles ROLE_USER and ROLE_TRANSLATOR at the same time");
//        }
        User user = getById(updateUserRolesInput.getUserId());
        user.setRoles(defineIdForExistingRoles(updateUserRolesInput.getRoles()));

        return userRepository.save(user);
    }

    @Override
    public User updateApnTokenByUserId(UpdateUserApnTokenInput updateUserApnTokenInput) {
        User user = getById(updateUserApnTokenInput.getUserId());
        user.setApnToken(updateUserApnTokenInput.getApnToken());
        return userRepository.save(user);
    }

    @Override
    public User updateFcmTokenByUserId(UpdateUserFcmTokenInput updateUserFcmTokenInput) {
        User user = getById(updateUserFcmTokenInput.getUserId());
        user.setFcmToken(updateUserFcmTokenInput.getFcmToken());
        return userRepository.save(user);
    }

    @Override
    public User deleteApnTokenByUserId(Long id) {
        User user = getById(id);
        user.setApnToken(null);
        return userRepository.save(user);
    }

    @Override
    public User deleteFcmTokenByUserId(Long id) {
        User user = getById(id);
        user.setFcmToken(null);
        return userRepository.save(user);
    }
}
