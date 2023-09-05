package com.habsida.moragoproject.model.entity;

import com.habsida.moragoproject.audit.AuditableEntity;
import com.habsida.moragoproject.model.enums.EFAQCategory;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "frequently_asker_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FrequentlyAskedQuestion extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;
    private EFAQCategory category;
    private String question;
}
