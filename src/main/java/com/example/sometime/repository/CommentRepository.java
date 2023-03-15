package com.example.sometime.repository;

import com.example.sometime.domain.Board;
import com.example.sometime.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByBoard(Board board);


    //deleteById(id)

}
