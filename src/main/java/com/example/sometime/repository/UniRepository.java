package com.example.sometime.repository;

import com.example.sometime.domain.Uni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniRepository extends JpaRepository<Uni, Long> {

    Optional<Uni> findByName(String name);
}
