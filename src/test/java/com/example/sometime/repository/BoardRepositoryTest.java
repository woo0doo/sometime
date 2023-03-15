package com.example.sometime.repository;

import com.example.sometime.domain.Board;
import com.example.sometime.domain.Category;
import com.example.sometime.domain.Uni;
import com.example.sometime.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class BoardRepositoryTest {

    @Autowired
    UniRepository uniRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void 생성_조회() throws Exception {
        //given
        Uni uni = createUni("수원대학교");
        User user = createUser("우영두", "18007073", "duden5000@naver.com", "zz", "키키", uni);
        Category category = createCategory("free", uni);
        Category category2 = createCategory("nono", uni);
        Board board1 = createBoard("영두", "헤헤", true, category, user);
        Board board2 = createBoard("원석", "호호영두", true, category, user);
        Board board3 = createBoard("진욱", "하하", true, category, user);
        Board board4 = createBoard("성욱", "케케", true, category, user);
        Board board5 = createBoard("성욱", "케케", true, category2, user);

        //when
        uniRepository.save(uni);
        userRepository.save(user);
        categoryRepository.save(category);
        categoryRepository.save(category2);
        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
        boardRepository.save(board4);
        boardRepository.save(board5);
        List<Uni> uniList = uniRepository.findAll();
        List<User> userList = userRepository.findAll();
        List<Category> categoryList = categoryRepository.findAll();
        List<Board> boardList = boardRepository.findByCategory(category);
        //then
        assertThat(uniList.size()).isEqualTo(1);
        assertThat(userList.size()).isEqualTo(1);
        assertThat(categoryList.size()).isEqualTo(2);
        assertThat(boardList.size()).isEqualTo(4);          //카테고리 별 조회 성공
    }




    @Test
    public void 검색() throws Exception {
        //given
        Uni uni = createUni("수원대학교");
        User user = createUser("우영두", "18007073", "duden5000@naver.com", "zz", "키키", uni);
        Category category = createCategory("free", uni);
        Category category2 = createCategory("nono", uni);
        Board board1 = createBoard("영두", "헤헤", true, category, user);
        Board board2 = createBoard("케케", "호호영두", true, category, user);
        Board board3 = createBoard("ㅇㅇ", "하하", true, category, user);
        Board board4 = createBoard("성욱", "케케", true, category, user);
        Board board5 = createBoard("성욱", "케케", true, category2, user);

        //when
        uniRepository.save(uni);
        userRepository.save(user);
        categoryRepository.save(category);
        categoryRepository.save(category2);
        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
        boardRepository.save(board4);
        boardRepository.save(board5);

        List<Board> boardList = boardRepository.findByTitleContainingOrContentContaining("영두", "영두");
        List<Board> boardList2 = boardRepository.findByTitleContainingOrContentContaining("케케", "케케");
        //then
        assertThat(boardList.size()).isEqualTo(2);
        assertThat(boardList2.size()).isEqualTo(3);
        for (Board board : boardList) {
            System.out.println("영두 = " + board);
        }

        for (Board board : boardList2) {
            System.out.println("케케 = " + board);
        }
                 //카테고리 별 조회 성공
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