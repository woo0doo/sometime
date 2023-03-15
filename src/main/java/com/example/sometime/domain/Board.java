package com.example.sometime.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Board extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title", nullable = false)
    private String title;

    @Column(name = "board_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    private Boolean is_anonymous;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<UserLikeBoard> userLikeBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<UserScrapBoard> userScrapBoardList = new ArrayList<>();

    // TODO. comment count

    // 생성 메서드
    @Builder
    public Board(String title, String content, Boolean is_anonymous, Category category, User user) {
        this.title = title;
        this.content = content;
        this.is_anonymous = is_anonymous;
        this.category = category;
        this.user = user;

        category.getBoardList().add(this);
    }

    /**
     * 비즈니스 로직
     * @param title
     * @param content
     */
    public void change(String title,String content) {
        this.title = title;
        this.content = content;
    }


    /**
     * <== 비즈니스 로직 ==>
     * 게시판 글 전체 조회
     */


}
