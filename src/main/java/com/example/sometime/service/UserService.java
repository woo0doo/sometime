package com.example.sometime.service;


import com.example.sometime.domain.*;
import com.example.sometime.dto.user.UserJoinDto;
import com.example.sometime.exception.AppException;
import com.example.sometime.exception.ErrorCode;
import com.example.sometime.repository.*;
import com.example.sometime.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final UserLikeBoardRepository userLikeBoardRepository;
    private final UserLikeCommentRepository userLikeCommentRepository;
    private final UserScrapBoardRepository userScrapBoardRepository;
    private final UniRepository uniRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60L;
    @Transactional //변경
    public Long save(User user) {
//        validateDuplicateMember(user); //중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }

    @Transactional //변경
    public void join(UserJoinDto userJoinDto) {
        // userName 중복 check,               nickname은 보류
        userRepository.findByEmail(userJoinDto.getEmail())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, userJoinDto.getEmail() +"는 이미 있습니다.");
    });
        // 대학교가 없으면 등록
        Optional<Uni> optionalUni = uniRepository.findByName(userJoinDto.getUniName());
        Uni uni = Uni.builder()
                .name(userJoinDto.getUniName()).build();
        if (optionalUni.isEmpty()) {
            uniRepository.save(uni);
        }
        else {
            uni = optionalUni.get();
        }
        // 저장
        User user = User.builder()
                .name(userJoinDto.getName())
                .studentNumber(userJoinDto.getStudentNumber())
                .email(userJoinDto.getEmail())
                .password(encoder.encode(userJoinDto.getPassword()))
                .nickname(userJoinDto.getNickname())
                .uni(uni)
                .build();;
        userRepository.save(user);

    }

    public String login(String email, String password) {
        //userName없음
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, email + "이 없습니다."));

        //password틀림
        if(!encoder.matches(password,user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 했습니다.");
        }

        String token = JwtTokenUtil.createToken(user.getEmail(), key,expireTimeMs);
        //앞에서 Exception안났으면 토큰 발행
        return token;
    }

    public void cancelLikeComment(Long userLikeCommentId) {     //유저 댓글 좋아요 삭제
        UserLikeComment userLikeComment = userLikeCommentRepository.findById(userLikeCommentId).get();
        User user = userLikeComment.getUser();
        Comment comment = userLikeComment.getComment();
        user.getUserLikeCommentList().remove(userLikeComment);
        comment.getUserLikeCommentList().remove(userLikeComment);
        userLikeCommentRepository.deleteById(userLikeCommentId);
    }

    public void cancelLikeBoard(Long userLikeBoardId) {         //유저 게시판 좋아요 삭제
        UserLikeBoard userLikeBoard = userLikeBoardRepository.findById(userLikeBoardId).get();
        User user = userLikeBoard.getUser();
        Board board = userLikeBoard.getBoard();
        user.getUserLikeBoardList().remove(userLikeBoard);
        board.getUserLikeBoardList().remove(userLikeBoard);
        userLikeBoardRepository.deleteById(userLikeBoardId);
    }

    public void cancelScrapBoard(Long userScrapBoardId) {
        UserScrapBoard userScrapBoard = userScrapBoardRepository.findById(userScrapBoardId).get();
        User user = userScrapBoard.getUser();
        Board board = userScrapBoard.getBoard();
        user.getUserScrapBoardList().remove(userScrapBoard);
        board.getUserScrapBoardList().remove(userScrapBoard);
        userScrapBoardRepository.deleteById(userScrapBoardId);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);

    }
    public User findByEmail(String email) { return userRepository.findByEmail(email).get();}
    public User findOne(Long userId) {
        return userRepository.findById(userId).get();
    }

    //    @Transactional //변경
//    public UserDto createUser(UserJoinDto userJoinDto) {
//
//        //이메일 중복 확인
//        if(userRepository.findByEmail(userJoinDto.getEmail()) != null){
//            return null;
//        }
//
//        userJoinDto.setPassword(bCryptPasswordEncoder.encode(userJoinDto.getPassword()));
//
//        User user = userJoinDto.toEntity();
//        return UserDto.builder()
//                .id(user.getId())
//                .name(user.getName())
//                .email(user.getEmail())
//                .studentNumber(user.getStudentNumber())
//                .nickname(user.getNickname())
//                .uni(user.getUni())
//                .build();
//
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByEmail(userEmail);
//        if (user.isEmpty()) {
//            throw new UsernameNotFoundException("이메일 주소를 확인해주세요.");
//        }
//        return user.get();
//    }
//
//    public User authenticate(String email, String password) {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//        if(optionalUser.isPresent()) {
//            log.info("@Authenticate: PW 확인");
//            User user = optionalUser.get();
//            if(passwordEncoder.matches(password, user.getPassword())){
//            } else{
//                throw new BadCredentialsException("Wrong password");
//            }
//        } else{
//            throw new UsernameNotFoundException("[" + email + "] Username Not Found.");
//        }
//        return optionalUser.get();
//
//    }
}
