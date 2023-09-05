package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.audit.AuditableEntity;
import com.habsida.moragoproject.model.enums.EPlatform;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
