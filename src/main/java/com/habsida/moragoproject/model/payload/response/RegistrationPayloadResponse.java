package com.habsida.moragoproject.model.payload.response;

import com.habsida.moragoproject.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationPayloadResponse {
    private User user;
    private String refreshToken;

}
