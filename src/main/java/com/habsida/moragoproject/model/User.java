package com.habsida.moragoproject.model;

import com.habsida.moragoproject.model.userProperty.Role;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "apn_token")
    private String apnToken;
    @Column(name = "balance")
    private Double balance;
    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "fcm_token")
    private String fcmToken;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "is_debtor")
    private Boolean isDebtor;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "on_boarding_status")
    private Integer onBoardingStatus;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "ratings")
    private Double ratings;
    @Column(name = "total_ratings")
    private Integer totalRatings;
    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Role> roles;

}
