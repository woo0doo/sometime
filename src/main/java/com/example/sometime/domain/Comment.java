package com.example.sometime.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;


    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private boolean is_anonymous;

    @OneToMany(mappedBy = "userlikecomment_id")
    private List<UserLikeComment> userLikeCommentList;

    @Builder
    public Comment(String content, User user, Board board, boolean is_anonymous, List<UserLikeComment> userLikeCommentList) {
        this.content = content;
        this.user = user;
        this.board = board;
        this.is_anonymous = is_anonymous;
        this.userLikeCommentList = userLikeCommentList;
    }
    // TODO. 카운트
}
