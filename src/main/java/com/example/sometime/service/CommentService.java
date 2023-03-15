package com.example.sometime.service;


import com.example.sometime.domain.Board;
import com.example.sometime.domain.Comment;
import com.example.sometime.domain.User;
import com.example.sometime.dto.BoardDto;
import com.example.sometime.dto.CommentDto;
import com.example.sometime.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private CommentRepository commentRepository;

    private BoardService boardService;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentDto> findByBoard(Long boardId) {     //게시판 별 댓글 조회
        List<Comment> commentList = commentRepository.findByBoard(boardService.find(boardId));
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDto commentDto = transform(comment);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    public void deleteComment(Long commentId) {         //댓글 삭제
        Comment comment = commentRepository.findById(commentId).get();
        User user = comment.getUser();
        Board board = comment.getBoard();
        user.getCommentList().remove(comment);      //연관관계 제거
        board.getCommentList().remove(comment);
        commentRepository.deleteById(commentId);
    }



    public CommentDto transform(Comment comment) {
        CommentDto commentDto = CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .board(comment.getBoard())
                .is_anonymous(comment.getIs_anonymous()).build();
        return commentDto;
    }
}
