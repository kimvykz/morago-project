package com.habsida.moragoproject.model.payload;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.entity.UserProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Setter
@Getter
@ToString
public class CurrentUserPayload {
    private Long id;
    private String apnToken;
    private Float balance;
    private String fcmToken;
    private String firstName;
    private Boolean isActive;
    private Boolean isDebtor;
    private String lastName;
    private Integer onBoardingStatus;
    private String phone;
    private Double ratings;
    private Integer totalRatings;
    private List<Role> roles;
    private UserProfile userProfile;
    private TranslatorProfile translatorProfile;
    private String whoAmI;
}
