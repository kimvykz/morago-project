package com.habsida.moragoproject.model.userProperty;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "themes")
@Data
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "description")
    private String description;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "is_popular")
    private Boolean isPopular;
    @Column(name = "korean_title")
    private String koreanTitle;
    @Column(name = "name")
    private String name;
    @Column(name = "night_price")
    private Double nightPrice;
    @Column(name = "price")
    private Double price;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "theme_id")
    private List<File> file;

}
