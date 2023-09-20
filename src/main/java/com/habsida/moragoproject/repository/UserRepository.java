package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
                value = "user-roles-profiles-graph")
    List<User> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            value = "user-roles-profiles-graph")
    Page<User> findAll(Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            value = "user-roles-profiles-graph")
    Optional<User> findById(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            value = "user-roles-profiles-graph")
    Optional<User> findByPhone(String phone);

    Boolean existsByPhone(String phone);

}
