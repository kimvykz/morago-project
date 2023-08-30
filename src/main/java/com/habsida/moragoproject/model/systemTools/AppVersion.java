package com.habsida.moragoproject.model.systemTools;

import com.habsida.moragoproject.model.dictionary.EPlatform;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_version")
@Data
public class AppVersion {
    @Id
    @Column(name = "platform")
    private EPlatform platform;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "latest")
    private String latest;
    @Column(name = "min")
    private String min;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
