package com.example.sometime.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;


    private String studentNumber;
    private String email;
    private String password;
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    private Uni uni;

    @OneToMany(mappedBy = "board_id")
    private List<Board> boardList;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "userlikeboard_id")
    private List<UserLikeBoard> userLikeBoardList;

    @OneToMany(mappedBy = "userlikecomment_id")
    private List<UserLikeComment> userLikeCommentList;

    @OneToMany(mappedBy = "userscrapboard_id")
    private List<UserScrapBoard> userScrapBoardList;

    @Builder
    public User(String name, String studentNumber, String email, String password, String nickname, Uni uni, List<Board> boardList, List<Comment> commentList, List<UserLikeBoard> userLikeBoardList, List<UserLikeComment> userLikeCommentList, List<UserScrapBoard> userScrapBoardList) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.uni = uni;
        this.boardList = boardList;
        this.commentList = commentList;
        this.userLikeBoardList = userLikeBoardList;
        this.userLikeCommentList = userLikeCommentList;
        this.userScrapBoardList = userScrapBoardList;
    }
}
