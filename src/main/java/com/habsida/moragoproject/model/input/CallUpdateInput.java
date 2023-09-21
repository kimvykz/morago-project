package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.enums.ECallStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CallUpdateInput {
    private Long id;
    private ECallStatus callStatus;
    private String channelName;
    private Double commission;
    private Integer duration;
    private Boolean isEndCall;
    private Boolean status;
    private Double sum;
    private Boolean translatorHasRated;
    private Boolean userHasRated;
    private File file;
    private Theme theme;
    private User caller;
    private User recipient;
}
