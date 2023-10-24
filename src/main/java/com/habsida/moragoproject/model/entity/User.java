package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedEntityGraph(name = "user-roles-profiles-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "roles"),
                @NamedAttributeNode(value = "userProfile"),
                @NamedAttributeNode(value = "translatorProfile"),
        })
@NamedEntityGraph(name = "user-roles-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "roles")
        })
//@NamedQuery(name = "User.findByPhone",
//        query = "SELECT u FROM User u JOIN FETCH u.roles WHERE u.phone = ?1")
public class User extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apnToken;
    private Float balance;
    private String fcmToken;
    private String firstName;
    private Boolean isActive;
    private Boolean isDebtor;
    private String lastName;
    private Integer onBoardingStatus;
    private String password;
    @Column(unique = true)
    private String phone;
    private Double ratings;
    private Integer totalRatings;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "translator_profile_id")
    private TranslatorProfile translatorProfile;

}
