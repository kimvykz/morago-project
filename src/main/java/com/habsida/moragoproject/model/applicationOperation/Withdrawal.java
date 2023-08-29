package com.habsida.moragoproject.model.applicationOperation;

import com.habsida.moragoproject.model.User;
import com.habsida.moragoproject.model.dictionary.EStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "withdrawals")
@Data
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "account_holder")
    private String accountHolder;
    @Column(name = "account_number")
    private String accountNumber;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "name_of_bank")
    private String nameOfBank;
    @Column(name = "status")
    private EStatus estatus;
    @Column(name = "sum")
    private Double sum;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

}
