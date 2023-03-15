package com.example.sometime.controller;

import com.example.sometime.dto.user.UserJoinDto;
import com.example.sometime.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;
    @Test
    public void 회원가입_성공() throws Exception {
        UserJoinDto userJoinDto = UserJoinDto.builder()
                .name("영두")
                .email("duden5000@naver.com")
                .nickname("깔깔")
                .uniName("수원대학교")
                .password("zkzkzk")
                .studentNumber("18007073")
                .build();

        mockMvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userJoinDto)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void 로그인_성공() throws Exception {
        String email = "duden5000@naver.com";
        String password = "1234";

//        when(userService.login(any(), any()))
//                .thenReturn("token");
//        mockMvc.perform(post("/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(new UserLoginDto(email,password))))
//                .andDo(print())
//                .andExpect(status().isOk());
    }

}