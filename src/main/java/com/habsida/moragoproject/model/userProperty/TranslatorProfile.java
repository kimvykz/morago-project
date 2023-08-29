package com.habsida.moragoproject.model.userProperty;

import com.habsida.moragoproject.model.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "translator_profiles")
@Data
public class TranslatorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "email")
    private String email;
    @Column(name = "is_available")
    private Boolean isAvailable;
    @Column(name = "is_online")
    private Boolean isOnline;
    @Column(name = "level_of_korean")
    private String levelOfKorean;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "translator_profile_id")
    private List<Language> languages;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "translator_profile_id")
    private List<Theme> themes;

}
