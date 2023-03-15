package com.example.sometime.repository;

import com.example.sometime.domain.Board;
import com.example.sometime.domain.User;
import com.example.sometime.domain.UserLikeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLikeBoardRepository extends JpaRepository<UserLikeBoard, Long> {
}
