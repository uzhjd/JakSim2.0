package com.twinkle.JakSim.auth;

import com.twinkle.JakSim.account.UserDO;
import com.twinkle.JakSim.account.UserDao;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DataSource ds;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String userId = token.getName();
        String userPw = (String) token.getCredentials();

        UserDao userDao = new UserDao(ds);
        UserDO userDO = userDao.findByUserId(userId);

        if(!passwordEncoder.matches(userPw, userDO.getPw()))
            throw new BadCredentialsException(userDO.getId());

        return new UsernamePasswordAuthenticationToken(userDO, userPw);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
