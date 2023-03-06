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
public class Comment extends BaseTimeEntity  {
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

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<UserLikeComment> userLikeCommentList = new ArrayList<>();;

    // TODO. 카운트

    // 생성자
    @Builder
    public Comment(String content, User user, Board board, boolean is_anonymous) {
        this.content = content;
        this.user = user;
        this.board = board;
        this.is_anonymous = is_anonymous;
    }
}
