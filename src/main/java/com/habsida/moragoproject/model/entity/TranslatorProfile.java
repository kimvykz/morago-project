package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "translator_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TranslatorProfile extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfBirth;
    private String email;
    private Boolean isAvailable;
    private Boolean isOnline;
    private String levelOfKorean;

    @OneToOne(mappedBy = "translatorProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "translator_profile_id")
    private List<Language> languages;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "translator_profile_id")
    private List<Theme> themes;
    
}
