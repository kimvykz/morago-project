package com.habsida.moragoproject.entity;

import com.habsida.moragoproject.audit.AuditableEntity;
import com.habsida.moragoproject.enums.EPlatform;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private EPlatform platform;
    private String latest;
    private String min;
}
