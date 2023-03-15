package com.example.sometime.repository;

import com.example.sometime.domain.*;
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
class CommentRepositoryTest {


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
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void 게시글당_댓글확인() throws Exception {
        //given
        Uni uni = createUni("수원대학교");
        User user = createUser("우영두", "18007073", "duden5000", "zz", "키키", uni);
        User user2 = createUser("우영두", "180070732", "duden50001", "zz", "키키", uni);
        User user3 = createUser("우영두", "180070733", "duden50002", "zz", "키키", uni);
        Category category = createCategory("free", uni);
        Category category2 = createCategory("nono", uni);
        Board board1 = createBoard("영두", "헤헤", true, category, user);
        Board board2 = createBoard("원석", "호호", true, category, user);

        Comment comment1 = createComment("키키", user, board1,true);
        Comment comment2 = createComment("키키", user2, board1,true);
        Comment comment3 = createComment("키키", user3, board1,true);
        Comment comment4 = createComment("키키", user, board2,true);
        Comment comment5 = createComment("키키", user, board2,true);

        //when

        uniRepository.save(uni);
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        categoryRepository.save(category);
        categoryRepository.save(category2);
        boardRepository.save(board1);
        boardRepository.save(board2);
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
        commentRepository.save(comment4);
        commentRepository.save(comment5);

        List<Comment> commentList = commentRepository.findByBoard(board1);

        //then
        assertThat(commentList.size()).isEqualTo(3);

    }

    @Test
    public void 댓글_삭제_게시글_확인() throws Exception {
        //given
        Uni uni = createUni("수원대학교");
        User user = createUser("우영두", "18007073", "duden5000", "zz", "키키", uni);
        User user2 = createUser("우영두", "180070732", "duden50001", "zz", "키키", uni);
        User user3 = createUser("우영두", "180070733", "duden50002", "zz", "키키", uni);
        Category category = createCategory("free", uni);
        Category category2 = createCategory("nono", uni);
        Board board1 = createBoard("영두", "헤헤", true, category, user);
        Board board2 = createBoard("원석", "호호", true, category, user);

        Comment comment1 = createComment("키키", user, board1,true);
        Comment comment2 = createComment("키키", user2, board1,true);
        Comment comment3 = createComment("키키", user3, board1,true);

        //when
        uniRepository.save(uni);
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        categoryRepository.save(category);
        categoryRepository.save(category2);
        boardRepository.save(board1);
        boardRepository.save(board2);
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);


        commentRepository.deleteById(1L);
        //then
        Board board = boardRepository.findById(1L).get();
        assertThat(board.getCommentList().size()).isEqualTo(2);
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

    public Comment createComment(String content, User user, Board board, boolean is_anonymous) {
        Comment comment = Comment.builder()
                .content(content)
                .user(user)
                .board(board)
                .is_anonymous(is_anonymous)
                .build();
        return comment;

    }

}