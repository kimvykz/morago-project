package com.habsida.moragoproject.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditingEntityListner {

    @PrePersist
    public void setCreatedAt(AuditableEntity auditableEntity){
        auditableEntity.setCreatedAt(LocalDateTime.now());
        auditableEntity.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void setUpdatedAt(AuditableEntity auditableEntity){
        auditableEntity.setUpdatedAt(LocalDateTime.now());
    }
}
