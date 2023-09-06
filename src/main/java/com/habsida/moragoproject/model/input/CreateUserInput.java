package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CreateUserInput {
    private String apnToken;
    private Float balance;
    private String fcmToken;
    private String firstName;
    private Boolean isActive;
    private Boolean isDebtor;
    private String lastName;
    private Integer onBoardingStatus;
    private String password;
    private String phone;
    private Double ratings;
    private Integer totalRatings;

    private List<Role> roles;

    //private UserProfile userProfile;

    //private TranslatorProfile translatorProfile;
}
