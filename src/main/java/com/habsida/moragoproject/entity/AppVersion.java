package com.habsida.moragoproject.entity;

import com.habsida.moragoproject.audit.AuditableEntity;
import com.habsida.moragoproject.enums.EPlatform;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_version")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppVersion extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "AppVersion platform cannot be null")
    @Column(unique = true)
    private EPlatform platform;
    private String latest;
    private String min;
}
