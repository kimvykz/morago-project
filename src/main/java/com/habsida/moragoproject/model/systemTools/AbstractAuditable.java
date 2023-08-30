package com.habsida.moragoproject.model.systemTools;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "abstract_auditable")
@Data
public class AbstractAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abs_per_id")
    private AbstractPesistable abstractPesistable;
}
