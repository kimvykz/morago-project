package com.habsida.moragoproject.dto;

import com.habsida.moragoproject.entity.File;
import com.habsida.moragoproject.entity.Theme;
import com.habsida.moragoproject.entity.User;
import com.habsida.moragoproject.enums.CallStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
public class CallDTO {
    private CallStatus callStatus;
    private String channelName;
    private Double commission;
    private Integer duration;
    private Boolean isEndCall;
    private Boolean status;
    private Double sum;
    private Boolean translatorHasRated;
    private Boolean userHasRated;
    //private File file;
    //private Theme theme;
    //private User caller;
    //private User recipient;
}
