package com.example.sometime.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(unique = true, length = 10)
    private String studentNumber;
    @Column(unique = true, length = 100)
    private String email;
    private String password;
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uni_id")
    private Uni uni;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLikeBoard> userLikeBoardList = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLikeComment> userLikeCommentList = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserScrapBoard> userScrapBoardList = new ArrayList<>();;


    @Builder
    public User(String name, String studentNumber, String email, String password, String nickname, Uni uni) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.uni = uni;
    }
}
