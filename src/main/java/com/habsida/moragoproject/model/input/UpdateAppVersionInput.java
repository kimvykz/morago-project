package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.enums.EPlatform;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UpdateAppVersionInput {
    private Long id;
    private EPlatform platform;
    private String latest;
    private String min;
}
