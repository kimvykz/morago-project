package com.habsida.moragoproject.model.applicationOperation;

import com.habsida.moragoproject.model.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "debtors")
@Data
public class Debtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "account_holder")
    private String accountHolder;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "is_paid")
    private Boolean isPaid;
    @Column(name = "name_of_bank")
    private String nameOfBank;
    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
