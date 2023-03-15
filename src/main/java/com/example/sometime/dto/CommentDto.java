package com.example.sometime.dto;


import com.example.sometime.domain.Board;
import com.example.sometime.domain.Comment;
import com.example.sometime.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private User user;
    private Board board;
    private Boolean is_anonymous;

    private Comment toEntity(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .content(content)
                .user(user)
                .board(board)
                .is_anonymous(is_anonymous).build();
        return comment;
    }
    @Builder
    public CommentDto(Long id,String content, User user, Board board, Boolean is_anonymous) {
        this.id=id;
        this.content = content;
        this.user = user;
        this.board = board;
        this.is_anonymous = is_anonymous;
    }
}
