package com.example.sometime.dto.user;


import com.example.sometime.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String studentNumber;

    private String nickname;
    private Uni uni;
    private List<Board> boardList = new ArrayList<>();;
    private List<Comment> commentList = new ArrayList<>();;
    private List<UserLikeBoard> userLikeBoardList = new ArrayList<>();;
    private List<UserLikeComment> userLikeCommentList = new ArrayList<>();;

    private List<UserScrapBoard> userScrapBoardList = new ArrayList<>();;

    public User toEntity(){
        User user = User.builder()
                .name(name)
                .email(email)
                .nickname(nickname)
                .uni(uni)
                .build();
        return user;
    }

    @Builder
    public UserDto(Long id, String name, String nickname, String email,String studentNumber,Uni uni) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.studentNumber=studentNumber;
        this.nickname=nickname;
        this.uni=uni;
    }
}
