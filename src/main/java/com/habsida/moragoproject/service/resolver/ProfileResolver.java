package com.habsida.moragoproject.service.resolver;


import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.payload.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ProfileResolver {

    @SchemaMapping(typeName = "User", field = "profile")
    public Profile getProfile(User user) {
        log.info("UserResolver.getProfile call -----------");
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

}
