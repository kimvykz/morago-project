package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfile extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isFreeCallMade;

    @OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
