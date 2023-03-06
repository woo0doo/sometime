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
public class Board extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title")
    private String title;

    @Column(name = "board_content", columnDefinition = "TEXT")
    private String content;

    private boolean is_anonymous;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "userlikeboard_id")
    private List<UserLikeBoard> userLikeBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "userscrapboard_id")
    private List<UserScrapBoard> userScrapBoardList = new ArrayList<>();

    // TODO. comment count

    // 생성 메서드
    @Builder
    public Board(String title, String content, boolean is_anonymous, Category category, User user, List<Comment> commentList) {
        this.title = title;
        this.content = content;
        this.is_anonymous = is_anonymous;
        this.category = category;
        this.user = user;
    }
}
