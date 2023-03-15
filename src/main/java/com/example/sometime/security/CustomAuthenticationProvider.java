package com.example.sometime.security;

import com.example.sometime.domain.User;
import com.example.sometime.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


//@RequiredArgsConstructor
//@Slf4j
//public class CustomAuthenticationProvider extends DaoAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {
//    private MessageSourceAccessor messages;
//
//    private UserService userService;
//
//    private final UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();
//
//    @Override
//    public void setMessageSource(MessageSource messageSource) {
//        this.messages = new MessageSourceAccessor(messageSource);
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = (String) authentication.getPrincipal();
//        String password = (String) authentication.getCredentials();
//
//        // 비밀번호 확인
//        User userDetails = userService.authenticate(username, password);
//
//        // 예외 발생 여부 확인
//        preAuthenticationChecks(userDetails);
//
//        return new UsernamePasswordAuthenticationToken(username, password, authentication.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//
//    private void preAuthenticationChecks(User userDetails) {            // 이건 나중에 쓸 예정.... ㅠㅠ
//        try {
//            userDetailsChecker.check(userDetails);
//        } catch (LockedException e) {
//            log.info("계정 잠금");
//            throw e;
//        } catch (DisabledException e) {
//            log.info("계정 유효 만료");
//            throw e;
//        } catch (AccountExpiredException e) {
//            log.info("계정 유효기한 만료");
//            throw e;
//        } catch (CredentialsExpiredException e) {
//            log.info("비밀번호 기한 만료");
//        } catch (UsernameNotFoundException e) {
//            log.info("계정 없음");
//            throw e;
//        }
//    }
//}
