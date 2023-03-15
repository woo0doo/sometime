package com.example.sometime.service;


import com.example.sometime.domain.Board;
import com.example.sometime.domain.Category;
import com.example.sometime.dto.BoardDto;
import com.example.sometime.repository.BoardRepository;
import com.example.sometime.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void saveBoard(BoardDto boardDto) {        //저장, 수정
        Board board = boardDto.toEntity();
        boardRepository.save(board);
    }

    public List<BoardDto> search(String term) {        // 검색
        List<Board> boardList = boardRepository.findByTitleContainingOrContentContaining(term, term);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardDto boardDto = transform(board); //dto변환 함수
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    public List<BoardDto> findBoardByCategory(Category category) {       // 카테고리 별 조회
        List<Board> boardList = boardRepository.findByCategory(category);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardDto boardDto = transform(board); //dto변환 함수
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    public BoardDto findBoard(Long id) {          // 특정 게시판 조회
        Board board = boardRepository.findById(id).get();
        BoardDto boardDto = transform(board);      //dto변환 함수
//        if (boardDto.getIs_anonymous() == true) {
//            //익명..
//        }
        return boardDto;
    }

    @Transactional
    public void deleteBoard(Long id) {              // 게시판 삭제
        Board board = boardRepository.findById(id).get();
        Category category = board.getCategory();
        category.getBoardList().remove(board);      //연관관계 제거
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(Long boardId, BoardDto boardDto) {
        Board board = boardRepository.findById(boardId).get();
        board.change(boardDto.getTitle(), boardDto.getContent());
        boardRepository.save(board);
    }


    public Board find(Long boardId) {     // 댓글찾기용 컨트롤러에서 사용금지
        Board board = boardRepository.findById(boardId).get();
        return board;
    }

        BoardDto transform(Board board) {
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .is_anonymous(board.getIs_anonymous())
                .category(board.getCategory())
                .user(board.getUser())
                .createdAt(board.getCreatedAt())
                .build();
        return boardDto;
    }


}
