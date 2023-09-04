package com.habsida.moragoproject.dto;

import com.habsida.moragoproject.enums.EPlatform;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class AppVersionDTO {
    private EPlatform platform;
    private String latest;
    private String min;
}
