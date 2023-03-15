package com.example.sometime.repository;

import com.example.sometime.domain.User;
import com.example.sometime.domain.UserLikeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByStudentNumber(String studentNumber);
    Optional<User> findByEmail(String email);
}
