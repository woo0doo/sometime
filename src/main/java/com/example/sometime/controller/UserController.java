package com.example.sometime.controller;


import com.example.sometime.domain.Category;
import com.example.sometime.dto.user.UserJoinDto;
import com.example.sometime.dto.user.UserLoginDto;
import com.example.sometime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public String menu(Authentication authentication, Model model) {
        String email = authentication.getName();
        List<Category> categoryList = userService.findByEmail(email).getUni().getCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "ok";
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinDto userJoinDto) {
        userService.join(userJoinDto);
        return ResponseEntity.ok().body("회원가입이 성공 했습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
        String token = userService.login(userLoginDto.getEmail(), userLoginDto.getPassword());
        return ResponseEntity.ok().body(token);
    }

}
