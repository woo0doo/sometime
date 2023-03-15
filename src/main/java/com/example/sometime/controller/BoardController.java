package com.example.sometime.controller;


import com.example.sometime.domain.Category;
import com.example.sometime.domain.User;
import com.example.sometime.dto.BoardDto;
import com.example.sometime.dto.CommentDto;
import com.example.sometime.service.BoardService;
import com.example.sometime.service.CategoryService;
import com.example.sometime.service.CommentService;
import com.example.sometime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{categoryId}")
public class BoardController {

    private final BoardService boardService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final CommentService commentService;


    @GetMapping // 카테고리별 게시판 조회
    public String list(@PathVariable("categoryId") Long categoryId, Model model) {
        List<BoardDto> boardDtoList =
                boardService.findBoardByCategory(categoryService.find(categoryId));
        model.addAttribute("boards",boardDtoList);

        return "ok";
    }

    @GetMapping("/board/{boardId}") //세부 게시판 조회
    public String boardSelect(@PathVariable String categoryId, @PathVariable("boardId") Long boardId, Model model) {
        BoardDto boardDto = boardService.findBoard(boardId);
        List<CommentDto> commentDtoList = commentService.findByBoard(boardId);
        model.addAttribute("board", boardDto);
        model.addAttribute("commentList", commentDtoList);

        return "ok";
    }

    @PostMapping("/board/{boardId}")   //세부 게시판 조회 post
    public String boardComment(@PathVariable String categoryId, @PathVariable("boardId") Long boardId, Model model) {


        return "ok";
    }
    @PostMapping("/board/save")         //게시글 작성
    public String boardSave(@PathVariable("categoryId") Long categoryId,
                            @ModelAttribute BoardDto boardDto,
                            Authentication authentication) {
        boardDto.setUser(userService.findByEmail(authentication.getName()));
        boardDto.setCategory(categoryService.find(categoryId));
        boardService.saveBoard(boardDto);
        return "ok";
    }

    @GetMapping("/board/{boardId}/update")      //업데이트 하라고 내려주는 글
    public String boardSelectUpdate(@PathVariable String categoryId, @PathVariable("boardId") Long boardId,
                                    Model model) {
        BoardDto boardDto = boardService.findBoard(boardId);
        model.addAttribute("boardDto",boardDto);
        return "ok";
    }
    @PostMapping("/board/{boardId}/update")         //업데이트 한 후 post
    public String boardUpdate(@PathVariable("categoryId") Long categoryId,
                              @PathVariable("boardId") Long boardId,
                              @ModelAttribute BoardDto boardDto) {
        boardService.updateBoard(boardId,boardDto);
        return "ok";
    }

    @PostMapping("/board")
    public ResponseEntity<String> writeBoard(Authentication authentication) {
        return ResponseEntity.ok().body(authentication.getName() + "님의 리뷰 등록이 완료 되었습니다.");
    }

}
