package com.example.sometime.service;

import com.example.sometime.domain.Uni;
import com.example.sometime.domain.User;
import com.example.sometime.dto.user.UserJoinDto;
import com.example.sometime.repository.CategoryRepository;
import com.example.sometime.repository.UniRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    BoardService boardService;
    @Autowired
    UniRepository uniRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Test
    public void 회원가입_성공() throws Exception {
        //given

        UserJoinDto userJoinDto = UserJoinDto.builder()
                .name("영두")
                .email("duden5000@naver.com")
                .password("dkdkdk")
                .nickname("키득")
                .studentNumber("18007073")
                .uniName("수원대학교")
                .build();
        //when
        userService.join(userJoinDto);
        //then
        User user = userService.findOne(1L);
        assertThat(user.getName()).isEqualTo("영두");
    }

    @Test()
    public void 회원가입_실패() throws Exception {
        //given
        Uni uni = Uni.builder()
                .name("수원대학교").build();
        UserJoinDto userJoinDto = UserJoinDto.builder()
                .name("영두")
                .email("duden5000@daum.com")
                .password("dkdkdk")
                .nickname("키득")
                .studentNumber("18007073")
                .uniName("수원대학교").build();
        //when
        userService.join(userJoinDto);
        //then
        assertThrows(RuntimeException.class,() -> userService.join(userJoinDto));
    }


}