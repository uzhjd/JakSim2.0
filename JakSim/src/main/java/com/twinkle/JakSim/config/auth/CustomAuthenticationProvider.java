package com.twinkle.JakSim.config.auth;

import com.twinkle.JakSim.model.service.account.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomUserDetailService customUserDetailService;
    private final FileService fileService;
    private final HttpSession session;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String)authentication.getCredentials(); //내가 적은 값이 들어오는 거임

        UserDetails user = customUserDetailService.loadUserByUsername(username);

        if(user ==  null){
            throw new UsernameNotFoundException(username);
        }
        if(!passwordEncoder.matches(password, user.getPassword())){
            System.out.println("비밀번호 다름");
            throw new BadCredentialsException("Invalid user Password");
        }

        session.setAttribute("session_user", user);
        session.setAttribute("session_profile", fileService.getSingeProfile(username));

        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class); //요기있네?!
    }
}
