package com.example.sometime.service;

import com.example.sometime.domain.Board;
import com.example.sometime.domain.Category;
import com.example.sometime.domain.Uni;
import com.example.sometime.domain.User;
import com.example.sometime.dto.BoardDto;
import com.example.sometime.repository.CategoryRepository;
import com.example.sometime.repository.UniRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class BoardServiceTest {

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
    public void 게시글_삭제_확인() throws Exception {
        //given
        Uni uni = createUni("수원대학교");
        User user = createUser("우영두3", "18007073", "duden5000@naver.com", "zz", "키키", uni);
        Category category = createCategory("free", uni);
        Board board1 = createBoard("영두", "헤헤", true, category, user);
        Board board2 = createBoard("케케", "호호영두", true, category, user);
        Board board3 = createBoard("ㅇㅇ", "하하", true, category, user);
        Board board4 = createBoard("성욱", "케케", true, category, user);
        //when
        uniRepository.save(uni);
        userService.save(user);
        categoryRepository.save(category);


        boardService.deleteBoard(board1.getId());

        List<BoardDto> boardDtoList = boardService.findBoardByCategory(category);

        //then
        assertThat(boardDtoList.size()).isEqualTo(3);

    }
    public Board createBoard(String title, String content, boolean is_anonymous,
                             Category category, User user) {
        Board board = Board.builder()
                .user(user)
                .category(category)
                .is_anonymous(is_anonymous)
                .title(title)
                .content(content)
                .build();
        return board;
    }

    public Uni createUni(String name) {
        Uni uni = Uni.builder()
                .name(name)
                .build();
        return uni;
    }
    public User createUser(String name, String studentNumber, String email, String password, String nickname, Uni uni) {
        User user = User.builder()
                .name(name)
                .studentNumber(studentNumber)
                .email(email)
                .password(password)
                .nickname(nickname)
                .uni(uni)
                .build();
        return user;
    }

    public Category createCategory(String name, Uni uni) {
        Category category = Category.builder()
                .name(name)
                .uni(uni)
                .build();
        return category;
    }

}