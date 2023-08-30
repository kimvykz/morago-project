package com.habsida.moragoproject.model.systemTools;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "abstract_persistable")
@Data
public class AbstractPesistable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
