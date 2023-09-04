package com.habsida.moragoproject.entity;

import com.habsida.moragoproject.audit.AuditableEntity;
import com.habsida.moragoproject.enums.EStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "withdrawals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Withdrawal extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountHolder;
    private String accountNumber;
    private String nameOfBank;
    private EStatus status;
    private Double sum;

    @ManyToOne (fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
