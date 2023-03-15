package com.example.sometime.repository;

import com.example.sometime.domain.*;
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
class UserLikeBoardRepositoryTest {

    @Autowired
    UniRepository uniRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserLikeBoardRepository userLikeBoardRepository;


    @Test
    public void 좋아요() throws Exception {
        //given
        Uni uni = createUni("수원대학교");
        User user = createUser("우영두", "18007073", "duden5000", "zz", "키키", uni);
        User user2 = createUser("우영두", "180070732", "duden50001", "zz", "키키", uni);
        User user3 = createUser("우영두", "180070733", "duden50002", "zz", "키키", uni);
        Category category = createCategory("free", uni);
        Category category2 = createCategory("nono", uni);
        Board board1 = createBoard("영두", "헤헤", true, category, user);
        Board board2 = createBoard("원석", "호호", true, category, user);

        UserLikeBoard userLikeBoard = createUserLikeBoard(user,board1);
        UserLikeBoard userLikeBoard2 = createUserLikeBoard(user,board2);
        UserLikeBoard userLikeBoard3 = createUserLikeBoard(user2,board1);
        UserLikeBoard userLikeBoard4 = createUserLikeBoard(user3,board2);
        //when

        uniRepository.save(uni);
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        categoryRepository.save(category);
        categoryRepository.save(category2);
        boardRepository.save(board1);
        boardRepository.save(board2);
        userLikeBoardRepository.save(userLikeBoard);
        userLikeBoardRepository.save(userLikeBoard2);
        userLikeBoardRepository.save(userLikeBoard3);
        userLikeBoardRepository.save(userLikeBoard4);



        //then
        User resUser = userRepository.findById(1L).get();
        Board resBoard = boardRepository.findById(1L).get();
        assertThat(resUser.getUserLikeBoardList().size()).isEqualTo(2);
        assertThat(resBoard.getUserLikeBoardList().size()).isEqualTo(2);

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

    public UserLikeBoard createUserLikeBoard(User user, Board board) {
        UserLikeBoard userLikeBoard = UserLikeBoard.builder()
                .user(user)
                .board(board)
                .build();
        return userLikeBoard;
    }
}