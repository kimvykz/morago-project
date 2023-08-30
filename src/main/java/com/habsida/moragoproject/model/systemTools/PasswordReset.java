package com.habsida.moragoproject.model.systemTools;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset")
@Data
public class PasswordReset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "phone")
    private String phone;
    @Column(name = "reset_code")
    private Integer resetCode;
    @Column(name = "token")
    private String token;
}
