package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.audit.AuditableEntity;
import com.habsida.moragoproject.model.enums.ECallStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "calls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Call extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "caller_id")
    private User caller;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id")
    private User recipient;
}
