package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.PasswordReset;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.*;
import com.habsida.moragoproject.model.payload.Profile;
import com.habsida.moragoproject.repository.PasswordResetRepository;
import com.habsida.moragoproject.repository.RoleRepository;
import com.habsida.moragoproject.repository.UserRepository;
import com.habsida.moragoproject.security.PasswordResetTokenGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private PasswordResetRepository passwordResetRepository;
    private PasswordResetTokenGenerator passwordResetTokenGenerator;
    public UserServiceImpl (UserRepository userRepository,
                            RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder,
                            PasswordResetRepository passwordResetRepository,
                            PasswordResetTokenGenerator passwordResetTokenGenerator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordResetRepository = passwordResetRepository;
        this.passwordResetTokenGenerator = passwordResetTokenGenerator;
    }

    private List<Role> assignIdToRoles (List<Role> roles) {
        List<Role> userRoles = new ArrayList<>();
        roles.stream().forEach( role -> {
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
        });
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
    public User create (UserCreateInput userCreateInput) {

        User user = new User();

        if (isExistsByPhone(user.getPhone())) {
            throw new KeyAlreadyExistsException("User is already existed with phone - "
                    + user.getPhone());
        }

        if (userCreateInput.getFirstName() == null) {
            throw new IllegalArgumentException("field firstName cannot be null");
        } else {
            user.setFirstName(userCreateInput.getFirstName());
        }
        if (userCreateInput.getLastName() == null) {
            throw new IllegalArgumentException("field lastName cannot be null");
        } else {
            user.setLastName(userCreateInput.getLastName());
        }
        if (userCreateInput.getPhone() == null
                || userCreateInput.getPhone().isBlank()) {
            throw new IllegalArgumentException("field phone cannot be Empty");
        } else {
            user.setPhone(userCreateInput.getPhone());
        }
        if (userCreateInput.getPassword() == null
                || userCreateInput.getPassword().isBlank()) {
            throw new IllegalArgumentException("field password cannot be Empty");
        } else {
            user.setPassword(passwordEncoder.encode(userCreateInput.getPassword()));
        }
        if (userCreateInput.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        } else {
            user.setIsActive(userCreateInput.getIsActive());
        }
        if (userCreateInput.getIsDebtor() == null) {
            throw new IllegalArgumentException("field isDebtor cannot be null");
        } else {
            user.setIsDebtor(userCreateInput.getIsDebtor());
        }
        if (userCreateInput.getRatings() == null) {
            throw new IllegalArgumentException("field rating cannot be null");
        } else {
            user.setRatings(userCreateInput.getRatings());
        }
        if (userCreateInput.getTotalRatings() == null) {
            throw new IllegalArgumentException("field totalRatings cannot be null");
        } else {
            user.setTotalRatings(userCreateInput.getTotalRatings());
        }
        if (userCreateInput.getBalance() == null) {
            throw new IllegalArgumentException("field balance cannot be null");
        } else {
            user.setBalance(userCreateInput.getBalance());
        }
        if (userCreateInput.getOnBoardingStatus() == null) {
            throw new IllegalArgumentException("field getOnBoardingStatus cannot be null");
        } else {
            user.setOnBoardingStatus(userCreateInput.getOnBoardingStatus());
        }
        if (userCreateInput.getApnToken() == null) {
            //user.setApnToken("token");  //here I need to know how to process this field
        } else {
            user.setApnToken(userCreateInput.getApnToken());
        }
        if (userCreateInput.getFcmToken() == null) {
            //user.setFcmToken("token");  //here I need to know how to process this field
        } else {
            user.setFcmToken(userCreateInput.getFcmToken());
        }
        if (userCreateInput.getRoles() == null) {
            throw new IllegalArgumentException("User must have at least 1 role");
        }  else {
            user.setRoles(userCreateInput.getRoles());
        }
        if (userCreateInput.getUserProfile() == null) {
            //here I need to know logic
        } else {
            user.setUserProfile(userCreateInput.getUserProfile());
        }
        if (userCreateInput.getTranslatorProfile() == null) {
            //here I need to know logic
        } else {
            user.setTranslatorProfile(userCreateInput.getTranslatorProfile());
        }

        user.setRoles(assignIdToRoles(user.getRoles()));
        return userRepository.save(user);
    }

    @Override
    public User update (UserUpdateInput userUpdateInput) {

        User user = getById(userUpdateInput.getId());

        if (userUpdateInput.getFirstName() != null
           && !userUpdateInput.getFirstName().isBlank()
            && !user.getFirstName().equals(userUpdateInput.getFirstName())) {
            user.setFirstName(userUpdateInput.getFirstName());
        }
        if (userUpdateInput.getLastName() != null
            && !userUpdateInput.getLastName().isBlank()
            && !user.getLastName().equals(userUpdateInput.getLastName())) {
            user.setLastName(userUpdateInput.getLastName());
        }
        if (userUpdateInput.getPhone() != null
            && !userUpdateInput.getPhone().isBlank()
            && !user.getPhone().equals(userUpdateInput.getPhone())) {
            user.setPhone(userUpdateInput.getPhone());
        }
        if (userUpdateInput.getPassword() != null
            && !userUpdateInput.getPassword().isBlank()
            && !user.getPassword().equals(passwordEncoder.encode(userUpdateInput.getPassword()))) {
            user.setPassword(passwordEncoder.encode(userUpdateInput.getPassword()));
        }
        if (userUpdateInput.getIsActive() != null
            && !user.getIsActive().equals(userUpdateInput.getIsActive())) {
            user.setIsActive(userUpdateInput.getIsActive());
        }
        if (userUpdateInput.getIsDebtor() != null
            && !user.getIsDebtor().equals(userUpdateInput.getIsDebtor())) {
            user.setIsDebtor(userUpdateInput.getIsDebtor());
        }
        if (userUpdateInput.getRatings() != null
            && !user.getRatings().equals(userUpdateInput.getRatings())) {
            user.setRatings(userUpdateInput.getRatings());
        }
        if (userUpdateInput.getTotalRatings() != null
            && !user.getTotalRatings().equals(userUpdateInput.getTotalRatings())) {
            user.setTotalRatings(userUpdateInput.getTotalRatings());
        }
        if (userUpdateInput.getBalance() != null
            && !user.getBalance().equals(userUpdateInput.getBalance())) {
            user.setBalance(userUpdateInput.getBalance());
        }
        if (userUpdateInput.getOnBoardingStatus() != null
            && !user.getOnBoardingStatus().equals(userUpdateInput.getOnBoardingStatus())) {
            user.setOnBoardingStatus(userUpdateInput.getOnBoardingStatus());
        }
        if (userUpdateInput.getApnToken() != null
            && !user.getApnToken().equals(userUpdateInput.getApnToken())) {
            user.setApnToken(userUpdateInput.getApnToken());
        }
        if (userUpdateInput.getFcmToken() != null
            && !user.getFcmToken().equals(userUpdateInput.getFcmToken())) {
            user.setFcmToken(userUpdateInput.getFcmToken());
        }
//        if (updateUserInput.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
//                .containsAll(Arrays.asList(ERole.ROLE_USER, ERole.ROLE_TRANSLATOR))) {
//            throw new IllegalArgumentException("User cannot have roles ROLE_USER and ROLE_TRANSLATOR at the same time");
//        }
//        if (updateUserInput.getRoles() != null
//                && !user.getRoles().equals(updateUserInput.getRoles())) {
//            user.setRoles(updateUserInput.getRoles());
//        }
        if (userUpdateInput.getUserProfile() != null
            //&& !user.getUserProfile().equals(userUpdateInput.getUserProfile())
        ) {
            user.setUserProfile(userUpdateInput.getUserProfile());
        }
        if (userUpdateInput.getTranslatorProfile() != null
            //&& !user.getTranslatorProfile().equals(userUpdateInput.getTranslatorProfile())
        ) {
            user.setTranslatorProfile(userUpdateInput.getTranslatorProfile());
        }

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
        return userRepository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
    }

    @Override
    public Boolean isExistsByPhone (String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public User updateRolesByUserId(UserRolesUpdateInput userRolesUpdateInput) {
        User user = getById(userRolesUpdateInput.getUserId());
        user.setRoles(assignIdToRoles(userRolesUpdateInput.getRoles()));

        return userRepository.save(user);
    }

    @Override
    public User updateApnTokenByUserId(UserApnTokenUpdateInput userApnTokenUpdateInput) {
        User user = getById(userApnTokenUpdateInput.getUserId());
        if (userApnTokenUpdateInput.getApnToken() != null
            && !userApnTokenUpdateInput.getApnToken().isBlank()
            && !user.getApnToken().equals(userApnTokenUpdateInput.getApnToken())) {
            user.setApnToken(userApnTokenUpdateInput.getApnToken());
            return userRepository.save(user);
        }
        return user;
    }

    @Override
    public User updateFcmTokenByUserId(UserFcmTokenUpdateInput userFcmTokenUpdateInput) {
        User user = getById(userFcmTokenUpdateInput.getUserId());
        if (userFcmTokenUpdateInput.getFcmToken() != null
            && !userFcmTokenUpdateInput.getFcmToken().isBlank()
            && !userFcmTokenUpdateInput.getFcmToken().isBlank()) {
            user.setFcmToken(userFcmTokenUpdateInput.getFcmToken());
            return userRepository.save(user);
        }
        return user;
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

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = getByPhone(authentication.getName());
        return user;
    }

    @Override
    public Profile getProfile(User user) {
        Profile profile = new Profile();

        if (user.getTranslatorProfile() != null) {
            profile.setLanguages(user.getTranslatorProfile().getLanguages());
            profile.setEmail(user.getTranslatorProfile().getEmail());
            profile.setThemes(user.getTranslatorProfile().getThemes());
            profile.setIsOnline(user.getTranslatorProfile().getIsOnline());
            profile.setDateOfBirth(user.getTranslatorProfile().getDateOfBirth());
            profile.setIsAvailable(user.getTranslatorProfile().getIsAvailable());
            profile.setLevelOfKorean(user.getTranslatorProfile().getLevelOfKorean());
            profile.setWhoAmI("TRANSLATOR");
        }
        if (user.getUserProfile() != null) {
            profile.setIsFreeCallMade(user.getUserProfile().getIsFreeCallMade());
            profile.setWhoAmI("USER");
        }

        return profile;
    }

    @Override
    public User updateIsAvailable(Boolean isAvailable) {
        UserUpdateInput userUpdateInput = new UserUpdateInput();
        User currentUser = getCurrentUser();
        if (!currentUser.getRoles().toString().contains("ROLE_TRANSLATOR")) {
            throw new IllegalArgumentException("Only translator can have isAvailable status");
        }
        TranslatorProfile translatorProfile = (currentUser.getTranslatorProfile() == null ?
                new TranslatorProfile() : currentUser.getTranslatorProfile());
        translatorProfile.setIsAvailable(isAvailable);
        userUpdateInput.setId(currentUser.getId());
        userUpdateInput.setTranslatorProfile(translatorProfile);
        return update(userUpdateInput);
    }

    @Override
    public User addFundsToBalance(Float addFunds) {
        User currentUser = getCurrentUser();
        currentUser.setBalance(currentUser.getBalance() + addFunds);
        return userRepository.save(currentUser);
    }

    @Override
    public User updatePassword(PasswordInput passwordInput) {
        PasswordReset passwordReset =
                passwordResetRepository.findByToken(passwordInput.getToken())
                        .orElseThrow(() -> new IllegalArgumentException("Reset data for user is not found by token - "
                                + passwordInput.getToken()));
        passwordResetTokenGenerator.verifyExpiration(passwordInput.getToken());
        if (passwordReset.getToken().equals(passwordInput.getToken())) {
            User user = getByPhone(passwordReset.getPhone());
            user.setPassword(passwordEncoder.encode(passwordInput.getPassword()));
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Validation of token is not successful");
        }
    }
}
