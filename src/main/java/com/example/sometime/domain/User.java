package com.example.sometime.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(unique = true, length = 10,nullable = false)
    private String studentNumber;
    @Column(unique = true, length = 100,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "uni_id")
    private Uni uni;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boardList = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLikeBoard> userLikeBoardList = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLikeComment> userLikeCommentList = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserScrapBoard> userScrapBoardList = new ArrayList<>();;

//    // 계정 잠김 여부
//    private boolean accountNonLocked = true;
//
//    // 사용 여부
//    private boolean enabled = true;
//
//    // 메일 수신 여부
//    private boolean receiveEmail = false;
//
//    // 회원 가입 일자
//    private LocalDate joinDate = LocalDate.now();
//
//    // 최근 로그인 일자
//    private LocalDate lastLoginDate;
//
//    // 1년 이상 로그인 하지 않을 시 계정 만료
//    private LocalDate accountExpiredDate;
//
//    // 3개월 마다 비밀번호 변경 필요
//    private LocalDate credentialsExpiredDate;
    @Builder
    public User(String name, String studentNumber, String email, String password, String nickname, Uni uni) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.uni = uni;

        //연관 관계 매서드
        uni.getUserList().add(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
