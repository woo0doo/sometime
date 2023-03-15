package com.example.sometime.repository;

import com.example.sometime.domain.UserLikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeCommentRepository extends JpaRepository<UserLikeComment, Long> {
}
