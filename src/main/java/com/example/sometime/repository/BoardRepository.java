package com.example.sometime.repository;

import com.example.sometime.domain.Board;
import com.example.sometime.domain.Category;
import com.example.sometime.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {


    // 검색 (제목 + 내용)
    List<Board> findByTitleContainingOrContentContaining(String term, String term2);
    List<Board> findByCategory(Category category);
}
