package com.example.sometime.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Blob;
import java.util.List;

public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;

    private boolean is_anonymous;
    private Category category;
    private User user;
    private List<Comment> commentList;

    // TODO. comment count
}
