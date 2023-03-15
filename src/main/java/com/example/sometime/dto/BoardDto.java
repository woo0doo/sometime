package com.example.sometime.dto;


import com.example.sometime.domain.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private Boolean is_anonymous;
    private Category category;
    private User user;
    private List<Comment> commentList = new ArrayList<>();
    private List<UserLikeBoard> userLikeBoardList = new ArrayList<>();
    private List<UserScrapBoard> userScrapBoardList = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Board toEntity(){
        Board board = Board.builder()
                .title(title)
                .content(content)
                .is_anonymous(is_anonymous)
                .category(category)
                .user(user)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String title, String content,Boolean is_anonymous,Category category,User user, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.is_anonymous = is_anonymous;
        this.category = category;
        this.user = user;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
