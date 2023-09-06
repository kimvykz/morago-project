package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.enums.ECallStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CreateCallInput {
    private ECallStatus ECallStatus;
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
