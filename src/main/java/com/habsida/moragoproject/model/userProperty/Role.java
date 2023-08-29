package com.habsida.moragoproject.model.userProperty;

import com.habsida.moragoproject.model.dictionary.ERole;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "name")
    private ERole name;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



}
