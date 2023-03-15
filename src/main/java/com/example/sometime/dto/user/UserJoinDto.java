package com.example.sometime.dto.user;


import com.example.sometime.domain.Uni;
import com.example.sometime.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserJoinDto {

    private String name;
    private String studentNumber;
    private String email;
    private String password;
    private String nickname;
    private String uniName;

    @Setter
    private Uni uni;

    @Builder
    public UserJoinDto(String name, String studentNumber, String email, String password, String nickname, String uniName) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.uniName = uniName;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .studentNumber(studentNumber)
                .email(email)
                .password(password)
                .nickname(nickname)
                .uni(uni)
                .build();
    }
}
