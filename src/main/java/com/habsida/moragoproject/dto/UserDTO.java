package com.habsida.moragoproject.dto;

import com.habsida.moragoproject.entity.Role;
import com.habsida.moragoproject.entity.TranslatorProfile;
import com.habsida.moragoproject.entity.UserProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDTO {
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

    //private List<Role> roles;

    //private UserProfile userProfile;

    //private TranslatorProfile translatorProfile;
}
