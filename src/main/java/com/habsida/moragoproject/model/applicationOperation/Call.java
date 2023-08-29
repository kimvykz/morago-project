package com.habsida.moragoproject.model.applicationOperation;

import com.habsida.moragoproject.model.User;
import com.habsida.moragoproject.model.dictionary.CallStatus;
import com.habsida.moragoproject.model.userProperty.File;
import com.habsida.moragoproject.model.userProperty.Theme;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "calls")
@Data
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "call_status")
    private CallStatus callStatus;
    @Column(name = "channel_name")
    private String channelName;
    @Column(name = "commission")
    private Double commission;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "is_end_call")
    private Boolean isEndCall;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "sum")
    private Double sum;
    @Column(name = "translator_has_rated")
    private Boolean translatorHasRated;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "user_has_rated")
    private Boolean userHasRated;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
