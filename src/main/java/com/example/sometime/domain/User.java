package com.example.sometime.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String studentNumber;
    private String email;
    private String password;
    private String nickname;

    private Uni uni;
    private List<Board> boardList;
    private List<Comment> commentList;

}
